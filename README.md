# TrueInternetAndroid



         allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
         
          
         dependencies {
	        compile 'com.github.shahariar007:TrueInternetAndroid:1.0.3'
	}

         new TrueNetwork(this).detect("YOUR_SERVER_IP", new NetworkResult() {

            @Override
            public void result(boolean status) {
                // do what you want based on status 
            }
            
        });
