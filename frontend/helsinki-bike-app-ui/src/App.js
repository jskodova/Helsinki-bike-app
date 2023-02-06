import AllJourneys from './pages/AllJourneys';
import AllStations from './pages/AllStations';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import React from 'react';
import './index.css';
import Layout from "./pages/Layout";

export default function App() {
    return (
      <BrowserRouter>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route index/>
          <Route path="journeys" element={<AllJourneys />} />
          <Route path="stations" element={<AllStations />} />
          <Route path="*"/>
        </Route>
      </Routes>
    </BrowserRouter>
    );
  }