import React from 'react';
import './App.css';
import journeys from "./components/AllJourneys";
import AllJourneys from "./components/AllJourneys";
import AllStations from "./components/AllStations";
import Nav from "./components/Nav";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

function App() {

  return (
    <Router>
      <div className="App">
        <Nav />
        <Routes>
          <Route path="/" exact component={Home} />
          <Route path="/journeys" render={() => <AllJourneys journeys={journeys} />} />
          <Route path="/stations" component={AllStations} />
        </Routes>
        <h1>Helsinki bike app</h1>
      </div>
    </Router>
  );
}

function Home() {
  return <h1>Home page</h1>;
}

export default App;