(ns shire-digest.generator.m
  "Generator using theme M."
  (:require [shire-digest.generator.core :refer [Generator generate]]
            [shire-digest.generator.utils :refer [write-to]]
            [shire-digest.meta.utils :refer [prepare-directory]]
            [clostache.parser :as tmpl]
            [clojure.java.io :as io]
            [me.raynes.fs :as fs]))


(def output-file-name "index.html")


(defn- get-tmpl
  "Get template real path."
  [tmpl-name]
  (str "m/" tmpl-name))


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


(defn- copy-static
  "Copy static files to output directory."
  [output-directory]
  (fs/copy-dir (.getFile (io/resource "m/static")) output-directory))


(deftype MGenerator [output-directory]
  Generator
  (generate [this posts]
    (let [dest (str output-directory "/" output-file-name)
          document (render-digest posts)]
      (write-to dest document))))


(defn create
  "Create a M generator.
  
  Options:
  
  - :dest output directory name."
  [options]
  (let [{:keys [dest]} options]
    (prepare-directory dest)
    (copy-static dest)
    (MGenerator. dest)))
