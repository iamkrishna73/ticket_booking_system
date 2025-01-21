import React, { useState, useEffect } from "react";
import { useForm } from "react-hook-form";
import { getStations, generateTicket } from "../services/api";
import StationSelector from "./StationSelector";

const TicketForm = () => {
  const [stations, setStations] = useState([]);
  const [ticketPrice, setTicketPrice] = useState(0);
  const [ticketId, setTicketId] = useState("");
  const { register, handleSubmit, watch, reset, formState: { errors } } = useForm();

  const startStation = watch("startStation");
  const endStation = watch("endStation");

  // Fetch stations on mount
  useEffect(() => {
    (async () => {
      const stationData = await getStations();
      setStations(stationData);
    })();
  }, []);

  // Calculate ticket price
  useEffect(() => {
    if (startStation && endStation) {
      const start = stations.find((s) => s.name === startStation)?.price || 0;
      const end = stations.find((s) => s.name === endStation)?.price || 0;
      setTicketPrice(Math.abs(start - end));
    }
  }, [startStation, endStation, stations]);

  // Handle ticket generation
  const onSubmit = async () => {
    try {
      const response = await generateTicket();
      setTicketId(response.ticketId);
      alert(`Ticket ID: ${response.ticketId}`);
      reset();
    } catch (error) {
      console.error("Error generating ticket", error);
    }
  };

  return (
    <div className="container mt-5">
      <h1 className="text-center text-primary mb-4">Metro Ticket Booking</h1>
      <form onSubmit={handleSubmit(onSubmit)} className="shadow p-4 rounded bg-light">
        <StationSelector
          stations={stations}
          label="Start Station"
          name="startStation"
          register={register}
          errors={errors}
        />
        <StationSelector
          stations={stations}
          label="End Station"
          name="endStation"
          register={register}
          errors={errors}
        />
        <div className="mb-3">
          <p className="fs-5">Ticket Price: <strong>${ticketPrice}</strong></p>
        </div>
        <button
          type="submit"
          className="btn btn-primary w-100"
          disabled={!startStation || !endStation}
        >
          Buy Ticket
        </button>
      </form>
      {ticketId && (
        <div className="alert alert-success mt-4" role="alert">
          Your Ticket ID: <strong>{ticketId}</strong>
        </div>
      )}
    </div>
  );
};

export default TicketForm;
