import frida
import sys
rdev = frida.get_remote_device()
session = rdev.attach("org.telegram.messenger")
scr = """
Interceptor.attach(
Module.findExportByName("libtmessages.00.so","sendRequest"),{
	onEnter: function(args){
           var param = Memory.readUtf0String(args[0])
           send("Param : "+param);
	}
}
);
"""

script = session.create_script(scr)
def on_message(message,data):
	print message
script.on("message",on_message)
script.load()
sys.stdin.read()
