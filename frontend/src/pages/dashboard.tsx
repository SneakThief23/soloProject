import React, {useEffect, useState} from "react";
import axios from "axios";

export default function Dashboard() {
    const [message, setMessage] = useState("Loading...");

    useEffect(() => {
        const token = localStorage.getItem("token");

    axios
      .get("http://localhost:8080/api/hello", {
        headers: { Authorization: `Bearer ${token}` },
      })
      .then((res) => setMessage(res.data))
      .catch(() => setMessage("Error fetching data"));
  }, []);

    return ( 
        <div className="flex flex-col items-center justify-center h-screen bg-gray-800 text-white">
        <h1 className="text-4xl font-bold mb-4">Dashboard</h1>
        <p>{message}</p>
        </div>
    );
};