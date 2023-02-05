import React, { useState, useEffect } from 'react';
import axios from 'axios';

const AllStations = () => {
  const [stations, setStations] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      const result = await axios.get('http://localhost:8080/api/stations');
      setStations(result.data);
    };
    fetchData();
  }, []);

  return (
    <div>
      <h1>All Stations</h1>
      {stations.map(station => (
        <div key={station[0]}>
          <h2>Station Name: {station[0]}</h2>
          <h2>Station Occurences: {station[1]}</h2>
        </div>
      ))}
    </div>
  );
};

export default AllStations;
