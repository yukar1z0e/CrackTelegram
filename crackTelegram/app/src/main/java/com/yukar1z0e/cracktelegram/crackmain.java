package com.yukar0z0e.cracktelegram;

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
import java.util.ArrayList;
import java.util.Collections;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.Objects;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeUnit;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.assetAsByteArray;
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
                    getIpInfo();
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
        final Class<?> TLRPC$BotInfoClass = findClass("org.telegram.tgnet.TLRPC$BotInfo", lpparam.classLoader);
        final Class<?> TLRPC$UserFullClass = findClass("org.telegram.tgnet.TLRPC$UserFull", lpparam.classLoader);
//        final Class<?> ContactsActivityClass=findClass("org.telegram.ui.ContactsActivity",lpparam.classLoader);
        final Class<?> AvatarDrawableClass = findClass("org.telegram.ui.Components.AvatarDrawable", lpparam.classLoader);
        final Class<?> UserObjectClass = findClass("org.telegram.messenger.UserObject", lpparam.classLoader);
        final Class<?> ProfileActivityClass = findClass("org.telegram.ui.ProfileActivity", lpparam.classLoader);
        final Class<?> TLRPC$TL_botCommandClass = findClass("org.telegram.tgnet.TLRPC$TL_botCommand", lpparam.classLoader);
        final Class<?> TLRPC$UserProfilePhotoClass = findClass("org.telegram.tgnet.TLRPC$UserProfilePhoto", lpparam.classLoader);
        final Class<?> TLRPC$UserStatusClass = findClass("org.telegram.tgnet.TLRPC$UserStatus", lpparam.classLoader);

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
                Field userField = findField(TLRPC$UserFullClass, "user");
                Object userObject = userField.get(param.args[0]);
                Field first_nameField = findField(TLRPC$UserClass, "first_name");
                Field last_nameField = findField(TLRPC$UserClass, "last_name");
                Field usernameField = findField(TLRPC$UserClass, "username");
                Field idField = findField(TLRPC$UserClass, "id");
                Field phoneField = findField(TLRPC$UserClass, "phone");
                Field lang_codeField = findField(TLRPC$UserClass, "lang_code");
                Field photoField = findField(TLRPC$UserClass, "photo");
                Object photoObject = photoField.get(userObject);
                Field photoidField = findField(TLRPC$UserProfilePhotoClass, "photo_id");
                Field statusField = findField(TLRPC$UserClass, "status");
                Object statusObject = statusField.get(userObject);
                Field expiresField = findField(TLRPC$UserStatusClass, "expires");
                Field restrictedField = findField(TLRPC$UserClass, "restricted");
                Field restriction_reasonField = findField(TLRPC$UserClass, "restriction_reason");
                Field mutual_contactField = findField(TLRPC$UserClass, "mutual_contact");
                Field explicit_contentField = findField(TLRPC$UserClass, "explicit_content");
                Field access_hashField = findField(TLRPC$UserClass, "access_hash");

                if (photoObject != null && statusObject != null) {
                    Log.d("crackTelegram",
                            "phone: " + phoneField.get(userObject)
                                    + ";id: " + idField.get(userObject)
                                    + ";username: " + usernameField.get(userObject)
                                    + ";bio: " + aboutField.get(param.args[0])
                                    + ";access_hash: " + access_hashField.get(userObject)
                                    + ";status: " + statusObject
                                    + ";status expires: " + expiresField.get(statusObject)
                                    + ";first name: " + first_nameField.get(userObject)
                                    + ";last name: " + last_nameField.get(userObject)
                                    + ";photo: " + photoObject
                                    + ";photo_id: " + photoidField.get(photoObject)
                                    + ";lang code: " + lang_codeField.get(userObject)
                                    + ";is restricted: " + restrictedField.get(userObject)
                                    + ";restriction reason: " + restriction_reasonField.get(userObject)
                                    + ";is mutual contact: " + mutual_contactField.get(userObject)
                                    + ";is explicit content: " + explicit_contentField.get(userObject)
                    );
                } else if (photoObject != null) {
                    Log.d("crackTelegram",
                            "phone: " + phoneField.get(userObject)
                                    + ";id: " + idField.get(userObject)
                                    + ";username: " + usernameField.get(userObject)
                                    + ";bio: " + aboutField.get(param.args[0])
                                    + ";access_hash: " + access_hashField.get(userObject)
                                    + ";status: " + statusObject
                                    + ";first name: " + first_nameField.get(userObject)
                                    + ";last name: " + last_nameField.get(userObject)
                                    + ";photo: " + photoObject
                                    + ";photo_id: " + photoidField.get(photoObject)
                                    + ";lang code: " + lang_codeField.get(userObject)
                                    + ";is restricted: " + restrictedField.get(userObject)
                                    + ";restriction reason: " + restriction_reasonField.get(userObject)
                                    + ";is mutual contact: " + mutual_contactField.get(userObject)
                                    + ";is explicit content: " + explicit_contentField.get(userObject)
                    );
                } else if (statusObject != null) {
                    Log.d("crackTelegram",
                            "phone: " + phoneField.get(userObject)
                                    + ";id: " + idField.get(userObject)
                                    + ";username: " + usernameField.get(userObject)
                                    + ";bio: " + aboutField.get(param.args[0])
                                    + ";access_hash: " + access_hashField.get(userObject)
                                    + ";status: " + statusObject
                                    + ";status expires: " + expiresField.get(statusObject)
                                    + ";first name: " + first_nameField.get(userObject)
                                    + ";last name: " + last_nameField.get(userObject)
                                    + ";photo: " + photoObject
                                    + ";lang code: " + lang_codeField.get(userObject)
                                    + ";is restricted: " + restrictedField.get(userObject)
                                    + ";restriction reason: " + restriction_reasonField.get(userObject)
                                    + ";is mutual contact: " + mutual_contactField.get(userObject)
                                    + ";is explicit content: " + explicit_contentField.get(userObject)
                    );
                } else {
                    Log.d("crackTelegram",
                            "phone: " + phoneField.get(userObject)
                                    + ";id: " + idField.get(userObject)
                                    + ";username: " + usernameField.get(userObject)
                                    + ";bio: " + aboutField.get(param.args[0])
                                    + ";access_hash: " + access_hashField.get(userObject)
                                    + ";status: " + statusObject
                                    + ";first name: " + first_nameField.get(userObject)
                                    + ";last name: " + last_nameField.get(userObject)
                                    + ";photo: " + photoObject
                                    + ";lang code: " + lang_codeField.get(userObject)
                                    + ";is restricted: " + restrictedField.get(userObject)
                                    + ";restriction reason: " + restriction_reasonField.get(userObject)
                                    + ";is mutual contact: " + mutual_contactField.get(userObject)
                                    + ";is explicit content: " + explicit_contentField.get(userObject)
                    );
                }
            }
        });

        findAndHookMethod(ProfileActivityClass, "setUserInfo", TLRPC$UserFullClass, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                Field bot_infoField = findField(TLRPC$UserFullClass, "bot_info");
                Object bot_infoObject = bot_infoField.get(param.args[0]);
                if (bot_infoObject != null) {
                    Field descriptionField = findField(TLRPC$BotInfoClass, "description");
                    Field user_idField = findField(TLRPC$BotInfoClass, "user_id");
                    Field commandsField = findField(TLRPC$BotInfoClass, "commands");
                    Log.d("crackTelegramBot", "user_id: " + user_idField.get(bot_infoObject)
                            + " description: " + descriptionField.get(bot_infoObject)
                            + " commands: " + commandsField.get(bot_infoObject));

                    ArrayList<?> commands = (ArrayList<?>) commandsField.get(bot_infoObject);
                    Field TL_botCommand_commandField = findField(TLRPC$TL_botCommandClass, "command");
                    Field TL_botCommand_descriptionField = findField(TLRPC$TL_botCommandClass, "description");
                    for (int i = 0; i < commands.size(); i++) {
                        Object tmpObj = commands.get(i);
                        Log.d("crackTelegramBot", " TL_botCommand_command: " + TL_botCommand_commandField.get(tmpObj)
                                + " TL_botCommand_description: " + TL_botCommand_descriptionField.get(tmpObj));
                    }
                }
            }
        });
    }

    public void getIpInfo() {

        //No LogCat
        final Class<?> ConnectionsManager$ResolvedDomainClass = findClass("org.telegram.tgnet.ConnectionsManager$ResolvedDomain", lpparam.classLoader);
        findAndHookConstructor(ConnectionsManager$ResolvedDomainClass, ArrayList.class, long.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                Log.d("crackTelegram", "ArrayList a: " + param.args[0] + " long t: " + param.args[0]);
            }
        });
        findAndHookMethod(ConnectionsManager$ResolvedDomainClass, "getAddress", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                Log.d("crackTelegram", "ret: " + param.getResult());
            }
        });

        //sendRequest
        final Class<?> ConnectionsManagerClass = findClass("org.telegram.tgnet.ConnectionsManager", lpparam.classLoader);
        final Class<?> TLObjectClass = findClass("org.telegram.tgnet.TLObject", lpparam.classLoader);
        final Class<?> RequestDelegateClass = findClass("org.telegram.tgnet.RequestDelegate", lpparam.classLoader);
        final Class<?> QuickAckDelegateClass = findClass("org.telegram.tgnet.QuickAckDelegate", lpparam.classLoader);
        final Class<?> WriteToSocketDelegateClass = findClass("org.telegram.tgnet.WriteToSocketDelegate", lpparam.classLoader);
        final Class<?> TLRPC$TL_users_getFullUserClass = findClass("org.telegram.tgnet.TLRPC$TL_users_getFullUser", lpparam.classLoader);
        final Class<?> TLRPC$InputUserClass = findClass("org.telegram.tgnet.TLRPC$InputUser", lpparam.classLoader);

        findAndHookMethod(ConnectionsManagerClass, "sendRequest", TLObjectClass, RequestDelegateClass, QuickAckDelegateClass, WriteToSocketDelegateClass, int.class, int.class, int.class, boolean.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                Log.d("crackTelegram", "TLObject: " + param.args[0] + " RequestDelegate: " + param.args[0] + " QuickAckDelegate: " + param.args[0] + " WriteToSocketDelegate: " + param.args[0] + " flags: " + param.args[0] + " datacenterId: " + param.args[0] + " connetionType: " + param.args[0] + " immediate: " + param.args[0]);
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                Log.d("crackTelegram", "return requestToken: " + param.getResult());
                Field idField = findField(TLRPC$TL_users_getFullUserClass, "id");
                if (param.args[0].getClass() == TLRPC$TL_users_getFullUserClass) {
                    Object idObject = idField.get(param.args[0]);

                    Field user_idField = findField(TLRPC$InputUserClass, "user_id");
                    Field access_hashField = findField(TLRPC$InputUserClass, "access_hash");
                    Object user_idObject = user_idField.get(idObject);
                    Object access_hashObject = access_hashField.get(idObject);

                    Log.d("crackTelegram", "user_id: " + user_idObject + " access_hash: " + access_hashObject);
                }
            }
        });

    }
}
