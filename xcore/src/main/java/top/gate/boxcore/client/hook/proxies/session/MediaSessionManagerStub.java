package top.gate.boxcore.client.hook.proxies.session;

import android.content.Context;

import java.lang.reflect.Method;

import mirror.android.media.session.ISessionManager;
import mirror.android.os.ServiceManager;
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
public class MediaSessionManagerStub extends BinderInvocationStub {

    public MediaSessionManagerStub() {
        super(ServiceManager.getService.call(Context.MEDIA_SESSION_SERVICE));
    }

    @Override
    protected Object getWho() {
        return ISessionManager.Stub.asInterface.call(ServiceManager.getService.call(Context.MEDIA_SESSION_SERVICE));
    }

    @Override
    protected void inject(Object baseInvocation, Object proxyInvocation) {
        replaceSystemService(Context.MEDIA_SESSION_SERVICE);
    }

    @Override
    public boolean isBadEnv() {
        return false;
    }

    @Override
    protected void onBindMethod() {
        super.onBindMethod();
        addMethodHook(new CreateSession());
    }

    static class CreateSession extends MethodHook {

        @Override
        protected String getMethodName() {
            return "createSession";
        }

        @Override
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            if (args != null && args.length > 0 && args[0] instanceof String) {
                args[0] = BBoxCore.getHostPkg();
            }
            return method.invoke(who, args);
        }
    }
}
