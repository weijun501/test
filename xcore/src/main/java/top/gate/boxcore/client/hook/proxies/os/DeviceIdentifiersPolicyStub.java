package top.gate.boxcore.client.hook.proxies.os;


import java.lang.reflect.Method;

import mirror.android.os.IDeviceIdentifiersPolicyService;
import mirror.android.os.ServiceManager;
import top.gate.boxcore.BBoxCore;
import top.gate.boxcore.client.hook.BinderInvocationStub;
import top.gate.boxcore.client.hook.MethodHook;
import top.gate.boxcore.utils.Md5Utils;

/**
 * * ∧＿∧
 * (`･ω･∥
 * 丶　つ０
 * しーＪ
 * 此处无Bug
 */
public class DeviceIdentifiersPolicyStub extends BinderInvocationStub {

    public DeviceIdentifiersPolicyStub() {
        super(ServiceManager.getService.call("device_identifiers"));
    }

    @Override
    protected Object getWho() {
        return IDeviceIdentifiersPolicyService.Stub.asInterface.call(ServiceManager.getService.call("device_identifiers"));
    }

    @Override
    protected void inject(Object baseInvocation, Object proxyInvocation) {
        replaceSystemService("device_identifiers");
    }

    @Override
    protected void onBindMethod() {
        super.onBindMethod();
        addMethodHook(new MethodHook() {
            @Override
            protected String getMethodName() {
                return "getSerialForPackage";
            }

            @Override
            protected Object hook(Object who, Method method, Object[] args) throws Throwable {
//                args[0] = BlackBoxCore.getHostPkg();
//                return method.invoke(who, args);
                return Md5Utils.md5(BBoxCore.getHostPkg());
            }
        });
    }

    @Override
    public boolean isBadEnv() {
        return false;
    }
}
