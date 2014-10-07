(ns shire-digest.generator.utils
  "Utilities.")


(defn write-to
  "Write something to stream."
  [stream content]
  (spit stream content))
