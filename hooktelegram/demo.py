import frida
import sys

redv=frida.get_remote_device()
session=redv.attach("org.telegram.messenger")

scr="""
Java.perform(function () {
        var nativePointer=Module.findExportByName("libtmessages.30.so","ConnectionSocket::openConnection")
        Interceptor.attach(nativePointer,{
            onEnter:function (args) {
                console.log("string: "+args[0]+" ushort: "+args[1]+" bool: "+args[2]+" int: "+args[3]);
            },
            onLeave:function (retval) {
                console.log("return");
            }
        });
    });
"""
script=session.create_script(scr)
def on_message(message,data):
    print message

script.on("message",on_message)
script.load()
sys.stdin.read()