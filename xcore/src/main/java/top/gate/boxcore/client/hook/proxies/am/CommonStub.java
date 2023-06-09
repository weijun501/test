package top.gate.boxcore.client.hook.proxies.am;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;

import java.lang.reflect.Method;

import top.gate.boxcore.BBoxCore;
import top.gate.boxcore.client.BClient;
import top.gate.boxcore.client.hook.MethodHook;
import top.gate.boxcore.client.hook.provider.FileProviderHandler;
import top.gate.boxcore.utils.ComponentUtils;
import top.gate.boxcore.utils.compat.BuildCompat;
import top.gate.boxcore.utils.compat.StartActivityCompat;

import static android.content.pm.PackageManager.GET_META_DATA;

/**
 * * ∧＿∧
 * (`･ω･∥
 * 丶　つ０
 * しーＪ
 * 此处无Bug
 */
public class CommonStub {
    public static final String TAG = "CommonStub";

    static class StartActivity extends MethodHook {
        @Override
        protected String getMethodName() {
            return "startActivity";
        }

        @Override
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Object arg = args[getIntentIndex()];
            if (arg instanceof Intent) {
                Intent intent = (Intent) arg;
                if (intent.getParcelableExtra("_VM_|_target_") != null) {
                    return method.invoke(who, args);
                }
                if (ComponentUtils.isRequestInstall(intent)) {
                    intent.setData(FileProviderHandler.convertFileUri(BClient.getApplication(), intent.getData()));
                    return method.invoke(who, args);
                }
                String dataString = intent.getDataString();
                if (dataString != null && dataString.equals("package:" + BClient.getVPackageName())) {
                    intent.setData(Uri.parse("package:" + BBoxCore.getHostPkg()));
                }

                ResolveInfo resolveInfo = BBoxCore.getBPackageManager().resolveActivity(
                        intent,
                        GET_META_DATA,
                        StartActivityCompat.getResolvedType(args),
                        BClient.getUserId());
                if (resolveInfo == null) {
                    return method.invoke(who, args);
                }

                intent.setExtrasClassLoader(who.getClass().getClassLoader());
                BBoxCore.getBActivityManager().startActivityAms(BClient.getUserId(),
                        StartActivityCompat.getIntent(args),
                        StartActivityCompat.getResolvedType(args),
                        StartActivityCompat.getResultTo(args),
                        StartActivityCompat.getResultWho(args),
                        StartActivityCompat.getRequestCode(args),
                        StartActivityCompat.getFlags(args),
                        StartActivityCompat.getOptions(args));
                return 0;
            }
            return method.invoke(who, args);
        }

        private int getIntentIndex() {
            if (BuildCompat.isR()) {
                return 3;
            } else {
                return 2;
            }
        }
    }

    static class StartActivities extends MethodHook {

        @Override
        protected String getMethodName() {
            return "startActivities";
        }

        @Override
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            Intent[] intents = (Intent[]) args[2];
            String[] resolvedTypes = (String[]) args[3];
            IBinder resultTo = (IBinder) args[4];
            Bundle options = (Bundle) args[5];
            // todo ??
            if (!ComponentUtils.isSelf(intents)) {
                return method.invoke(who, args);
            }

            for (Intent intent : intents) {
                intent.setExtrasClassLoader(who.getClass().getClassLoader());
            }
            return BBoxCore.getBActivityManager().startActivities(BClient.getUserId(),
                    intents, resolvedTypes, resultTo, options);
        }
    }

    static class ActivityResumed extends MethodHook {

        @Override
        protected String getMethodName() {
            return "activityResumed";
        }

        @Override
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            BBoxCore.getBActivityManager().onActivityResumed((IBinder) args[0]);
            return method.invoke(who, args);
        }
    }

    static class ActivityDestroyed extends MethodHook {

        @Override
        protected String getMethodName() {
            return "activityDestroyed";
        }

        @Override
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            BBoxCore.getBActivityManager().onActivityDestroyed((IBinder) args[0]);
            return method.invoke(who, args);
        }
    }

    static class FinishActivity extends MethodHook {

        @Override
        protected String getMethodName() {
            return "finishActivity";
        }

        @Override
        protected Object hook(Object who, Method method, Object[] args) throws Throwable {
            BBoxCore.getBActivityManager().onFinishActivity((IBinder) args[0]);
            return method.invoke(who, args);
        }
    }
}
