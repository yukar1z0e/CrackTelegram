package com.yukar1z0e.cracktelegram;

import android.app.Application;
import android.content.Context;
import android.os.FileObserver;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.MotionEvent;

import java.text.Format;
import java.util.Collections;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeUnit;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookConstructor;
import static de.robv.android.xposed.XposedHelpers.findClass;
import static de.robv.android.xposed.XposedHelpers.findField;
import static de.robv.android.xposed.XposedHelpers.callMethod;
import static de.robv.android.xposed.XposedHelpers.setObjectField;
import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

public class crackmain implements IXposedHookLoadPackage {
    private XC_LoadPackage.LoadPackageParam lpparam = null;

    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        if (loadPackageParam.packageName.contains("org.telegram")) {
            findAndHookMethod(Application.class, "attach", Context.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    lpparam = loadPackageParam;
                    getInfo();
                }
            });
        }
    }

    public void test() {
        Log.d("crackTelegram", "package name: " + lpparam.packageName);
    }

    public void getInfo() {
        final Class<?> TLRPC$UserClass = findClass("org.telegram.tgnet.TLRPC$User", lpparam.classLoader);
        final Class<?> TLRPC$UserFullClass = findClass("org.telegram.tgnet.TLRPC$UserFull", lpparam.classLoader);
//        final Class<?> ContactsActivityClass=findClass("org.telegram.ui.ContactsActivity",lpparam.classLoader);
        final Class<?> AvatarDrawableClass = findClass("org.telegram.ui.Components.AvatarDrawable", lpparam.classLoader);
        final Class<?> UserObjectClass = findClass("org.telegram.messenger.UserObject", lpparam.classLoader);
        final Class<?> ProfileActivityClass = findClass("org.telegram.ui.ProfileActivity", lpparam.classLoader);

//        findAndHookMethod(AvatarDrawableClass, "setInfo", TLRPC$UserClass, new XC_MethodHook() {
//            @Override
//            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                //Log.d("crackTelegram","Param[0]-->"+param.args[0]);
//                Field first_nameField = findField(TLRPC$UserClass, "first_name");
//                Field last_nameField = findField(TLRPC$UserClass, "last_name");
//                Field usernameField = findField(TLRPC$UserClass, "username");
//                Field idField = findField(TLRPC$UserClass, "id");
//                Field phoneField = findField(TLRPC$UserClass, "phone");
//                Field lang_codeField = findField(TLRPC$UserClass, "lang_code");
//                Field photoField = findField(TLRPC$UserClass, "photo");
//                Field restrictedField = findField(TLRPC$UserClass, "restricted");
//                Field restriction_reasonField = findField(TLRPC$UserClass, "restriction_reason");
//                Field statusField = findField(TLRPC$UserClass, "status");
//                Field mutual_contactField = findField(TLRPC$UserClass, "mutual_contact");
//                Field explicit_contentField = findField(TLRPC$UserClass, "explicit_content");
//                Log.d("crackTelegram",
//                        "phone: " + phoneField.get(param.args[0])
//                                + ";id: " + idField.get(param.args[0])
//                                + ";username: " + usernameField.get(param.args[0])
//                                + ";status: " + statusField.get(param.args[0])
//                                + ";first name: " + first_nameField.get(param.args[0])
//                                + ";last name: " + last_nameField.get(param.args[0])
//                                + ";photo: " + photoField.get(param.args[0])
//                                + ";lang code: " + lang_codeField.get(param.args[0])
//                                + ";is restricted: " + restrictedField.get(param.args[0])
//                                + ";restriction reason: " + restriction_reasonField.get(param.args[0])
//                                + ";is mutual contact: " + mutual_contactField.get(param.args[0])
//                                + ";is explicit content: " + explicit_contentField.get(param.args[0])
//                );
//            }
//        });

//        findAndHookConstructor(AvatarDrawableClass, TLRPC$UserClass,boolean.class,new XC_MethodHook() {
//            @Override
//            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                Field first_nameField = findField(TLRPC$UserClass, "first_name");
//                Field last_nameField = findField(TLRPC$UserClass, "last_name");
//                Field usernameField = findField(TLRPC$UserClass, "username");
//                Field idField = findField(TLRPC$UserClass, "id");
//                Field phoneField = findField(TLRPC$UserClass, "phone");
//                Field lang_codeField = findField(TLRPC$UserClass, "lang_code");
//                Field photoField = findField(TLRPC$UserClass, "photo");
//                Field restrictedField = findField(TLRPC$UserClass, "restricted");
//                Field restriction_reasonField = findField(TLRPC$UserClass, "restriction_reason");
//                Field statusField = findField(TLRPC$UserClass, "status");
//                Field mutual_contactField = findField(TLRPC$UserClass, "mutual_contact");
//                Field explicit_contentField = findField(TLRPC$UserClass, "explicit_content");
//                Log.d("crackTelegram",
//                        "phone: " + phoneField.get(param.args[0])
//                                + ";id: " + idField.get(param.args[0])
//                                + ";username: " + usernameField.get(param.args[0])
//                                + ";status: " + statusField.get(param.args[0])
//                                + ";first name: " + first_nameField.get(param.args[0])
//                                + ";last name: " + last_nameField.get(param.args[0])
//                                + ";photo: " + photoField.get(param.args[0])
//                                + ";lang code: " + lang_codeField.get(param.args[0])
//                                + ";is restricted: " + restrictedField.get(param.args[0])
//                                + ";restriction reason: " + restriction_reasonField.get(param.args[0])
//                                + ";is mutual contact: " + mutual_contactField.get(param.args[0])
//                                + ";is explicit content: " + explicit_contentField.get(param.args[0])
//                );
//            }
//        });

//        findAndHookMethod(UserObjectClass, "isDeleted", TLRPC$UserClass, new XC_MethodHook() {
//            @Override
//            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                Field first_nameField = findField(TLRPC$UserClass, "first_name");
//                Field last_nameField = findField(TLRPC$UserClass, "last_name");
//                Field usernameField = findField(TLRPC$UserClass, "username");
//                Field idField = findField(TLRPC$UserClass, "id");
//                Field phoneField = findField(TLRPC$UserClass, "phone");
//                Field lang_codeField = findField(TLRPC$UserClass, "lang_code");
//                Field photoField = findField(TLRPC$UserClass, "photo");
//                Field restrictedField = findField(TLRPC$UserClass, "restricted");
//                Field restriction_reasonField = findField(TLRPC$UserClass, "restriction_reason");
//                Field statusField = findField(TLRPC$UserClass, "status");
//                Field mutual_contactField = findField(TLRPC$UserClass, "mutual_contact");
//                Field explicit_contentField = findField(TLRPC$UserClass, "explicit_content");
//                Log.d("crackTelegram",
//                        "phone: " + phoneField.get(param.args[0])
//                                + ";id: " + idField.get(param.args[0])
//                                + ";username: " + usernameField.get(param.args[0])
//                                + ";status: " + statusField.get(param.args[0])
//                                + ";first name: " + first_nameField.get(param.args[0])
//                                + ";last name: " + last_nameField.get(param.args[0])
//                                + ";photo: " + photoField.get(param.args[0])
//                                + ";lang code: " + lang_codeField.get(param.args[0])
//                                + ";is restricted: " + restrictedField.get(param.args[0])
//                                + ";restriction reason: " + restriction_reasonField.get(param.args[0])
//                                + ";is mutual contact: " + mutual_contactField.get(param.args[0])
//                                + ";is explicit content: " + explicit_contentField.get(param.args[0])
//                );
//            }
//        });

        findAndHookMethod(ProfileActivityClass, "setUserInfo", TLRPC$UserFullClass, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                Field aboutField = findField(TLRPC$UserFullClass, "about");
//                Log.d("crackTelegram","about: "+aboutField.get(param.args[0]));
                Field userField = findField(TLRPC$UserFullClass, "user");
                Object userObject = userField.get(param.args[0]);
                Field first_nameField = findField(TLRPC$UserClass, "first_name");
                Field last_nameField = findField(TLRPC$UserClass, "last_name");
                Field usernameField = findField(TLRPC$UserClass, "username");
                Field idField = findField(TLRPC$UserClass, "id");
                Field phoneField = findField(TLRPC$UserClass, "phone");
                Field lang_codeField = findField(TLRPC$UserClass, "lang_code");
                Field photoField = findField(TLRPC$UserClass, "photo");
                Field restrictedField = findField(TLRPC$UserClass, "restricted");
                Field restriction_reasonField = findField(TLRPC$UserClass, "restriction_reason");
                Field statusField = findField(TLRPC$UserClass, "status");
                Field mutual_contactField = findField(TLRPC$UserClass, "mutual_contact");
                Field explicit_contentField = findField(TLRPC$UserClass, "explicit_content");
                Log.d("crackTelegram",
                        "phone: " + phoneField.get(userObject)
                                + ";id: " + idField.get(userObject)
                                + ";username: " + usernameField.get(userObject)
                                + ";bio: " + aboutField.get(param.args[0])
                                + ";status: " + statusField.get(userObject)
                                + ";first name: " + first_nameField.get(userObject)
                                + ";last name: " + last_nameField.get(userObject)
                                + ";photo: " + photoField.get(userObject)
                                + ";lang code: " + lang_codeField.get(userObject)
                                + ";is restricted: " + restrictedField.get(userObject)
                                + ";restriction reason: " + restriction_reasonField.get(userObject)
                                + ";is mutual contact: " + mutual_contactField.get(userObject)
                                + ";is explicit content: " + explicit_contentField.get(userObject)
                );
            }
        });
    }
}
