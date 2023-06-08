package top.gate.boxcore.client.hook.proxies.context.providers;

import android.os.IInterface;

import java.lang.reflect.Method;

import top.gate.boxcore.BBoxCore;
import top.gate.boxcore.client.hook.ClassInvocationStub;

/**
 * * ∧＿∧
 * (`･ω･∥
 * 丶　つ０
 * しーＪ
 * 此处无Bug
 */
public class ContentProviderStub extends ClassInvocationStub implements VContentProvider {
    public static final String TAG = "ContentProviderStub";
    private IInterface mBase;

    public IInterface wrapper(final IInterface contentProviderProxy, final String appPkg) {
        mBase = contentProviderProxy;
        injectHook();
        return (IInterface) getProxyInvocation();
    }

    @Override
    protected Object getWho() {
        return mBase;
    }

    @Override
    protected void inject(Object baseInvocation, Object proxyInvocation) {

    }

    @Override
    protected void onBindMethod() {

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if ("asBinder".equals(method.getName())) {
            return method.invoke(mBase, args);
        }
        if (args != null && args.length > 0 && args[0] instanceof String) {
            String pkg = (String) args[0];
            args[0] = BBoxCore.getHostPkg();
        }
        try {
            return method.invoke(mBase, args);
        } catch (Throwable e) {
            throw e.getCause();
        }
    }

    @Override
    public boolean isBadEnv() {
        return false;
    }
}
