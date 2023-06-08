package top.gate.boxcore.client.hook.proxies.am;

import mirror.android.app.IActivityTaskManager;
import mirror.android.os.ServiceManager;
import top.gate.boxcore.client.hook.BinderInvocationStub;

/**
 * * ∧＿∧
 * (`･ω･∥
 * 丶　つ０
 * しーＪ
 * 此处无Bug
 */
public class ActivityTaskManagerStub extends BinderInvocationStub {
    public static final String TAG = "ActivityTaskManager";

    public ActivityTaskManagerStub() {
        super(ServiceManager.getService.call("activity_task"));
    }

    @Override
    protected Object getWho() {
        return IActivityTaskManager.Stub.asInterface.call(ServiceManager.getService.call("activity_task"));
    }

    @Override
    protected void inject(Object baseInvocation, Object proxyInvocation) {
        replaceSystemService("activity_task");
    }

    @Override
    protected void onBindMethod() {
        addMethodHook(new CommonStub.StartActivity());
        addMethodHook(new CommonStub.StartActivities());
        addMethodHook(new CommonStub.ActivityDestroyed());
        addMethodHook(new CommonStub.ActivityResumed());
        addMethodHook(new CommonStub.FinishActivity());
    }

    @Override
    public boolean isBadEnv() {
        return false;
    }

}
