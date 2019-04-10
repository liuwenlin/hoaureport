// Place third party dependencies in the lib folder
//
// Configure loading modules from the lib directory,
// except 'app' ones,
requirejs.config({
    "baseUrl":AUTH_LIB_URL,
    "paths": {
        "app": "../plugin/js"
    }
});

// Load the main app module to start the app
requirejs([
	AUTH_JS_URL
]);
