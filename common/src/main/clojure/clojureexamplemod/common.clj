(ns clojureexamplemod.common
  (:import (org.apache.logging.log4j LogManager Logger)
           (net.minecraft.world.item Item CreativeModeTab))
  (:require [com.github.lukebemish.clojurewrapper.api.mod :as mod-api]
            [com.github.lukebemish.clojurewrapper.api.item :as item]
            [com.github.lukebemish.clojurewrapper.api.util :as util]
            [com.github.lukebemish.clojurewrapper.api.platform :as platform]))

(def ^String modid "clojure_example_mod")
(def ^Logger logger (LogManager/getLogger modid))

(defn init []
  ; mod-api is a general api for loading mods from a map
  (mod-api/mod-load
    {:main
     (fn []
       (do
         (. logger info "Loading Example Mod!"))
       ; run-platform lets you keep forge-only and fabric-only code seperate; sided/run-sided can be used to do the same
       ; for client and server code.
       (platform/run-platform 'clojureexamplemod.setup/events))
     :client
     (fn []
       (do
         (. logger info "This only logs on the client...")))
     :server
     (fn []
       (do
         (. logger info "This only logs on the server...")))
     ; cross-platform registry compatibility. See com.github.lukebemish.clojurewrapper.impl.registries/register-objects
     ; for available registries.
     :registries
     {:items
      {(util/resource-location modid "test_item")
       #(proxy [Item] [(item/item-properties {:tab CreativeModeTab/TAB_MISC})]
          (isFoil [is] true))
       }}
     }))

