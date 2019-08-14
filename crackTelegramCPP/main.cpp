#include <iostream>
#include <windows.h>

using namespace std;

void writeReslutToLog(){
    string command = R"(adb logcat crackTelegram:D *:S *:W *:E *:F *:S -f /data/local/tmp/telegram.txt)";
    WinExec(command.c_str(),1);
}

void pullReslut() {
    string command = R"(adb pull /data/local/tmp/telegram.txt C:\Users\yukar1z0e\Desktop)";
    system(command.c_str());
}

void getResult() {
    FILE *fid = fopen("C:/Users/yukar1z0e/Desktop/telegram.txt", "r");
    char line[2048];
    memset(line, 0, 2048);
    while (!feof(fid)) {
        fgets(line, 2048, fid);
        cout << line << endl;
    }
}

void search(string phoneNumber) {
    string command;
    //Start Telegram
    command="adb shell am start -n org.telegram.messenger/org.telegram.ui.LaunchActivity";
    system(command.c_str());
    //Tap Write Icon
    command="adb shell input tap 715 1100";
    system(command.c_str());
    //Tap Add Friend Icon
    command="adb shell input tap 715 1100";
    system(command.c_str());
    //first name is single_search
    command="adb shell input text single_search";
    system(command.c_str());
    //tap country code
    command="adb shell input tap 105 560";
    system(command.c_str());
    //Del
    command="adb shell input keyevent 67";
    system(command.c_str());
    //input country code
    command="adb shell input text 852";
    system(command.c_str());
    //tab
    command="adb shell input keyevent 61";
    system(command.c_str());
    //input phone number
    command = "adb shell input text " + phoneNumber;
    system(command.c_str());
    //tap add
    command="adb shell input tap 715 105";
    system(command.c_str());
    //return and kill process
    command="adb shell am force-stop org.telegram.messenger";
    system(command.c_str());
    //restart
    command="adb shell am start -n org.telegram.messenger/org.telegram.ui.LaunchActivity";
    system(command.c_str());
    //tap friends card(or blank)
    command="adb shell input tap 715 670";
    system(command.c_str());
    //tap avator(or blank)
    command="adb shell input tap 400 100";
    system(command.c_str());
    //after that show everything
    //del friend
    //tap 'more'(or search icon)
    command="adb shell input tap 715 110";
    system(command.c_str());
    //tap del(or blank)
    command="adb shell input tap 715 400";
    system(command.c_str());
    //tap confirm(or key 'o')
    command="adb shell input tap 650 730";
    system(command.c_str());
    //kill process
    command="adb shell am force-stop org.telegram.messenger";
    system(command.c_str());

    cout<<"继续查询"<<endl;
}

void closeKeyboard(){
    string command="adb shell input keyevent 4";
    system(command.c_str());
}

void readFile() {
    FILE *fid = fopen("C:/Users/yukar1z0e/Desktop/telegram.txt", "r");
    char line[2048];
    memset(line, 0, 2048);
    while (!feof(fid)) {
        fgets(line, 2048, fid);
        cout << line << endl;
    }
}

int main() {
    FILE *fid = fopen("C:/Users/yukar1z0e/Desktop/1.txt", "r");
    char line[2048];
    memset(line, 0, 2048);
    writeReslutToLog();
    while (!feof(fid)) {
        fgets(line, 2048, fid);
        string phoneNumber = line;
        if (phoneNumber == "0") {
            break;
        }
        search(phoneNumber);
    }
    closeKeyboard();
    pullReslut();
    getResult();
    cout << "查询结束" << endl;
    return 0;
}

/*int main() {
    while (true)
    {
        string phoneNumber;
        cout << "输入手机号" << endl;
        cin >> phoneNumber;
        writeReslutToLog();
        if(phoneNumber=="0"){
            break;
        }
        search(phoneNumber);
        pullReslut();
        getResult();
    }
    cout<<"查询结束"<<endl;
    return 0;
}*/
