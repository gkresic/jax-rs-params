# jax-rs-params
Test case for https://issues.redhat.com/browse/RESTEASY-2484

Build: `./gradlew clean build`

Run: `./gradlew run --console=plain`

# Tests

* `curl http://127.0.0.1:8100/foo?entity=a`
* `curl http://127.0.0.1:8100/bar?entity=b`
* `curl http://127.0.0.1:8100/foo/bar?entity=b` [FAIL]
