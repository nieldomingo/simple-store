(ns simple-store.core)

(def storage (atom {}))

(defn main [req]
  (let [method (:request-method req)
        uri (:uri req)]
    (cond
      (= method :get)
      (if-let [obj (get @storage uri)]
        {:status 200 :body obj}
        {:status 404 :body (str uri " not found !")})
      (= method :post)
      (if-let [body (slurp (:body req))]
        (and
          (swap! storage assoc uri body)
          {:status 200 :body "post successfull!"})
        {:status 400 :body "must provide body"})
      (= method :delete)
      (and
        (swap! storage dissoc uri)
        {:status 200 :body (str "removed " uri)})
      :default {:status 404 :body "not found!"})))
