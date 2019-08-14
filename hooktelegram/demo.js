Interceptor.attach(Module.findExportByName("libc.so" , "open"), {
    onEnter: function(args) {
        log("open() called!")
    },
    onLeave:function(retval){
    
    }
});

// frida -U -n org.potato.messenger -l demo.js -o android.util.log.log