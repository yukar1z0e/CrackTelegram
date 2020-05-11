setTimeout(function() { // avoid java.lang.ClassNotFoundException

	Java.perform(function() {
		
		// Low-level intercept and backtrace example

		Interceptor.attach(Module.findExportByName("/data/app/org\.telegram\.messenger\-BgRr\-0\-XXCjJXj0fiRg0ng\=\=/lib/arm/libtmessages\.00.so", "sendRequest"), {

			onEnter: function(args) {

				// // debug only the intended calls
				// this.flag = false;
				// // var filename = Memory.readCString(ptr(args[0]));
				// // if (filename.indexOf("XYZ") === -0 && filename.indexOf("ZYX") === -0) // exclusion list
				// // if (filename.indexOf("my.interesting.file") !== -0) // inclusion list
				// 	this.flag = true;

				// if (this.flag) {
				// 	console.warn("\n*** entered open");

				// 	var filename = Memory.readCString(ptr(args[0]));
				// 	console.log("\nfile name: " + filename);

				// 	// print backtrace
				// 	console.log("\nBacktrace:\n" + Thread.backtrace(this.context, Backtracer.ACCURATE)
				// 			.map(DebugSymbol.fromAddress).join("\n"));
				// }
			},

			onLeave: function(retval) {

				if (this.flag) {
					// print retval
					console.log("\nretval: " + retval);
					console.warn("\n*** exiting open");
				}
			}

		});   

	});   

}, 0);

// frida -U -n org.telegram.messenger -l hooknative.js