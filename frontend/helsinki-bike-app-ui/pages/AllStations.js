import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Table from 'react-bootstrap/Table';
import Pagination from 'react-js-pagination';
import '../App.css';

function AllStations() {
  const [stations, setStations] = useState([]);
  const [activePage, setActivePage] = useState(1);
  const [itemsPerPage] = useState(10);
  const [totalItems, setTotalItems] = useState(0);

  useEffect(() => {
    const fetchData = async () => {
      const result = await axios.get('http://localhost:8080/api/stations');
      setStations(result.data);
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
  const currentItems = stations.slice(indexOfFirstItem, indexOfLastItem);

  return (
    <div>
    <h2 className="text-center mt-5 ">List of Stations</h2>
    <div className="container mt-2">
    <Table striped bordered hover variant="dark" className="table table-bordered border-info">
        <thead>
            <tr>
              <th>Station ID</th>
              <th>Station Name</th>
              <th>Address</th>
              <th>Count of Departure Stations</th>
              <th>Count of Returning Stations</th>
              <th>Average Distance Departure Stations</th>
              <th>Average Distance Returning Stations</th>
            </tr>
        </thead>
        <tbody>
          {currentItems.map(station => (
            <tr key={station.stationID}>
            <td>{station.stationID}</td>
            <td>{station.stationName}</td>
            <td>{station.address}</td>
            <td>{station.countDep}</td>
            <td>{station.countRet}</td>
            <td>{station.avgDep}</td>
            <td>{station.avgRet}</td>
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

export default AllStations;