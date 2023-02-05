import React, { useState, useEffect } from 'react';
import axios from 'axios';


export default function AllJourneys() {
    const [journeys, setJourneys] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
          const result = await axios.get('http://localhost:8080/api/journeys');
          setJourneys(result.data);
        };
        fetchData();
      }, [journeys]);
  return (
    <div>
      <h1>All Journeys</h1>
      {journeys.map(journey => (
        <div key={journey.journeyID}>
          <h2>Journey ID: {journey.journeyID}</h2>
          <p>Departure Time: {journey.departureTime}</p>
          <p>Return Time: {journey.returnTime}</p>
          <p>Departure Station ID: {journey.depStationID}</p>
          <p>Departure Station Name: {journey.depStationName}</p>
          <p>Return Station ID: {journey.retStationID}</p>
          <p>Return Station Name: {journey.retStationName}</p>
          <p>Distance: {journey.distance}</p>
          <p>Duration: {journey.duration}</p>
        </div>
      ))}
    </div>
  );
};