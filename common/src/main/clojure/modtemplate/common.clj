(ns modtemplate.common
  (:import (org.apache.logging.log4j LogManager Logger)
           (net.minecraft.world.item Item CreativeModeTab))
  (:require [com.github.lukebemish.clojurewrapper.api.mod :as mod-api]
            [com.github.lukebemish.clojurewrapper.api.item :as item]
            [com.github.lukebemish.clojurewrapper.api.util :as util]))

(def ^String modid "mod_template")
(def ^Logger logger (LogManager/getLogger modid))

(defn init []
  (mod-api/mod-load
    {:main
     (fn []
       (do
         (. logger info "Loading Template Mod!")))
     :registries
     {:item
      {(util/resource-location modid "test_item")
       #(proxy [Item] [(item/item-properties {
                                              :fire-resistant true
                                              :tab            CreativeModeTab/TAB_MISC
                                              })]
          (isFoil [is] true))
       }}
     }))

