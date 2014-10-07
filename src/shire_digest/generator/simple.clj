(ns shire-digest.generator.simple
  "Simple HTML generator."
  (:require [shire-digest.generator.core :refer [Generator create]]
            [shire-digest.generator.utils :refer [write-to]]
            [clostache.parser :as tmpl]))


(def output-document "simple.html")

(defn- get-tmpl
  "Get template real path."
  [tmpl-name]
  (str "simple/" tmpl-name))

(defn- render-tmpl
  "Render a template."
  [tmpl-name vars]
  (tmpl/render-resource (get-tmpl tmpl-name) vars))

(defn- render-article
  "Render an article from post meta."
  [post-meta]
  (render-tmpl "article.mustache" post-meta))

(defn- render-digest
  "Render digest from post metas."
  [post-metas]
  (let [articles (map render-article post-metas)]
    (render-tmpl "digest.mustache" {:articles articles})))

(deftype SimpleGenerator [options]
  Generator
  (create [this posts]
    (let [dest (str (:dest options) "/" output-document)
          document (render-digest posts)]
      (write-to dest document))))
