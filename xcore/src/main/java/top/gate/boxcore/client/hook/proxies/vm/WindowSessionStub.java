package top.gate.boxcore.client.hook.proxies.vm;

import android.os.IInterface;
import android.view.WindowManager;

import java.lang.reflect.Method;

import top.gate.boxcore.BBoxCore;
import top.gate.boxcore.client.hook.BinderInvocationStub;
import top.gate.boxcore.client.hook.MethodHook;


/**
 * * ∧＿∧
 * (`･ω･∥
 * 丶　つ０
 * しーＪ
 * 此处无Bug
 */
public class WindowSessionStub extends BinderInvocationStub {
    public static final String TAG = "WindowSessionStub";

    private IInterface mSession;

    public WindowSessionStub(IInterface session) {
        super(session.asBinder());
        mSession = session;
    }

    @Override
    protected Object getWho() {
        return mSession;
    }

    @Override
    protected void inject(Object baseInvocation, Object proxyInvocation) {

    }

    @Override
    public boolean isBadEnv() {
        return false;
    }

    @Override
    protected void onBindMethod() {
        super.onBindMethod();
        addMethodHook(new AddToDisplay());
        addMethodHook(new AddToDisplayAsUser());
    }

    @Override
    public Object getProxyInvocation() {
        return super.getProxyInvocation();
    }

    public static class AddToDisplay extends MethodHook {

        @Override
        protected String getMethodName() {
            return "addToDisplay";
        }

        @Override
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            for (Object arg : args) {
                if (arg == null) {
                    continue;
                }
                if (arg instanceof WindowManager.LayoutParams) {
                    ((WindowManager.LayoutParams) arg).packageName = BBoxCore.getHostPkg();
                }
            }
            return method.invoke(who, args);
        }
    }

    public static class AddToDisplayAsUser extends AddToDisplay {

        @Override
        protected String getMethodName() {
            return "addToDisplayAsUser";
        }
    }
}
