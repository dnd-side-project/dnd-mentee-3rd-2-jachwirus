import React, { useState, useEffect } from "react";
import axios from "axios";

const useFetch = ({ api, option, callback }) => {
  const [loading, setLoading] = useState(false);

  const fetchData = async () => {
    setLoading(true);
    const { data } = await axios.get(api, option);
    callback(data);
    setLoading(false);
  };

  useEffect(() => {
    fetchData();
  }, []);

  return loading;
};

export default useFetch;
