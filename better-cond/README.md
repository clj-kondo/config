# Better-Cond

Defines a hook that is compatible with the
[better-cond](https://github.com/Engelberg/better-cond) library,
which offers a practical generalization of the cond macro.
The hook supports all the better-cond constructs in both
keyword and symbol form.

The `.clj-kondo` directory points to the
`../../resources/clj-kondo.exports/clj-kondo/better-cond`
directory, which contains the configuration for the hook.
See also the docstring in the hook source file.

The test file `test/clj_condo/better_cond_test.clj` can be
run through clj-kondo on the command line to test the
hook. For instance:

```
  clj-kondo --lang clj --lint test/clj_kondo/better_cond_test.clj
```

