(ns shire-digest.generator.core
  "Generator protocol.")

(defprotocol Generator
  "Generator protocol.
  Each generator should take generate-options and list of post metas and
  create a document."
  
  (create [this posts] "Create a document."))
