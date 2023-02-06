import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Table from 'react-bootstrap/Table';
import Pagination from 'react-js-pagination';
import '../App.css';

const AllJourneys = () => {
    const [journeys, setJourneys] = useState([]);
    const [activePage, setActivePage] = useState(1);
    const [itemsPerPage] = useState(10);
    const [totalItems, setTotalItems] = useState(0);
  
    useEffect(() => {
      const fetchData = async () => {
        const result = await axios.get('http://localhost:8080/api/journeys');
        setJourneys(result.data);
        setTotalItems(result.data.length);
      };
      fetchData();
    }, []);
  
    //Handle page change
    const handlePageChange = (pageNumber) => {
      setActivePage(pageNumber);
    };
  
    // Get current posts
    const indexOfLastItem = activePage * itemsPerPage;
    const indexOfFirstItem = indexOfLastItem - itemsPerPage;
    const currentItems = journeys.slice(indexOfFirstItem, indexOfLastItem);
  
    return (
      <div>
      <h1>Helsinki bike app - Solita</h1>
      <h2 className="text-center mt-5 ">List of Journeys</h2>
      <div className="container mt-2">
      <Table striped bordered hover variant="dark" className="table table-bordered border-info">
          <thead>
              <tr>
                <th>Departing Station ID</th>
                <th>Departing Station Name </th>
                <th>Returning Station ID</th>
                <th>Returning Station Name </th>
                <th>Distance in kms</th>
                <th>Duration in s.</th>
              </tr>
          </thead>
          <tbody>
            {currentItems.map(journey => (
              <tr key={journey.journeyID}>
              <td>{journey.depStationID}</td>
              <td>{journey.depStationName}</td>
              <td>{journey.retStationID}</td>
              <td>{journey.retStationName}</td>
              <td>{journey.distance}</td>
              <td>{journey.duration}</td>
            </tr>
            ))}
          </tbody>
        </Table>
        <div className="d-flex justify-content-center mt-2">
          <Pagination
            activePage={activePage}
            itemsCountPerPage={itemsPerPage}
            totalItemsCount={totalItems}
            pageRangeDisplayed={5}
            onChange={handlePageChange}
          />
        </div>
      </div>
  </div>);
  };
  export default AllJourneys;
