test_cmd = clojure -M:test


e2e-tests:
	$(test_cmd) features
