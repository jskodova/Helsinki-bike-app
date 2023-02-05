import React, { useState, useEffect } from 'react';
import axios from 'axios';

const SingleStation = (props) => {
  const [station, setStation] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      const result = await axios.get(`http://localhost:8080/api/stations/${props.match.params.stationID}`);
      setStation(result.data);
    };
    fetchData();
  }, []);

  return (
    <div>
      <h1>Station ID: {station.stationID}</h1>
      <p>Station Name: {station.stationName}</p>
      <p>Number of Departures: {station.countDep}</p>
      <p>Number of Returns: {station.countRet}</p>
    </div>
  );
};

export default SingleStation;