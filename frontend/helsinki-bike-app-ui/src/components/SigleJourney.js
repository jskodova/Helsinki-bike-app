import React, { useState, useEffect } from 'react';
import axios from 'axios';

const SingleJourney = (props) => {
  const [journey, setJourney] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      const result = await axios.get(`http://localhost:8080/api/journeys/${props.match.params.journeyID}`);
      setJourney(result.data);
    };
    fetchData();
  }, []);

  return (
    <div>
      <h1>Journey ID: {journey.journeyID}</h1>
      <p>Departure Time: {journey.departureTime}</p>
      <p>Return Time: {journey.returnTime}</p>
      <p>Departure Station ID: {journey.depStationID}</p>
      <p>Departure Station Name: {journey.depStationName}</p>
      <p>Return Station ID: {journey.retStationID}</p>
      <p>Return Station Name: {journey.retStationName}</p>
      <p>Distance: {journey.distance}</p>
      <p>Duration: {journey.duration}</p>
    </div>
  );
};

export default SingleJourney;
