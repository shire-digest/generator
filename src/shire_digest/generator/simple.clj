(ns shire-digest.generator.simple
  "Simple HTML generator."
  (:require [shire-digest.generator.core :refer [Generator generate]]
            [shire-digest.generator.utils :refer [write-to]]
            [shire-digest.meta.utils :refer [prepare-directory today]]
            [clostache.parser :as tmpl]))


(def output-file-name "simple.html")

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

(deftype SimpleGenerator [output-directory]
  Generator
  (generate [this posts]
    (let [dest (str output-directory "/" output-file-name)
          document (render-digest posts)]
      (write-to dest document))))

(defn create
  "Create a simple generator.

  Options:

  - :dest output directory name, subdirectory will be divided by date.
  "
  [options]
  (let [{:keys [dest]} options
        today-directory (str dest "/" (today))]
    (prepare-directory today-directory)
    (SimpleGenerator. today-directory)))
