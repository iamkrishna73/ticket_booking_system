import axios from "axios";

const api = axios.create({
  baseURL: "http://localhost:8182/api", // Replace with your backend URL
});

export const getStations = async () => {
  const response = await api.get("/stations");
  return response.data;
};

export const generateTicket = async () => {
  const response = await api.post("/tickets/generate");
  return response.data;
};
