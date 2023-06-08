package top.gate.boxcore.client.hook.proxies.view;

import java.lang.reflect.Method;

import mirror.android.os.ServiceManager;
import mirror.android.view.IGraphicsStats;
import top.gate.boxcore.client.hook.BinderInvocationStub;
import top.gate.boxcore.client.hook.MethodHook;
import top.gate.boxcore.utils.MethodParameterUtils;

/**
 * * ∧＿∧
 * (`･ω･∥
 * 丶　つ０
 * しーＪ
 * 此处无Bug
 */
public class GraphicsStatsStub extends BinderInvocationStub {

    public GraphicsStatsStub() {
        super(ServiceManager.getService.call("graphicsstats"));
    }

    @Override
    protected Object getWho() {
        return IGraphicsStats.Stub.asInterface.call(ServiceManager.getService.call("graphicsstats"));
    }

    @Override
    protected void inject(Object baseInvocation, Object proxyInvocation) {
        replaceSystemService("graphicsstats");
    }

    @Override
    public boolean isBadEnv() {
        return false;
    }

    @Override
    protected void onBindMethod() {
        super.onBindMethod();
        addMethodHook(new RequestBufferForProcess());
    }

    static class RequestBufferForProcess extends MethodHook {

        @Override
        protected String getMethodName() {
            return "requestBufferForProcess";
        }

        @Override
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            MethodParameterUtils.replaceFirstAppPkg(args);
            return method.invoke(who, args);
        }
    }
}
