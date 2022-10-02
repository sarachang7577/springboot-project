import axios from "axios";
const url = "http://localhost:8080";

export const createStation = (stationObj) => axios.post(url + "/stations", stationObj);
export const getStations = () => axios.get(url + "/stations");
export const updateStationById = (stationId, stationObj) => axios.put(url + "/stations/" + stationId, stationObj);
export const deleteStationById = (stationId) => axios.delete(url + "/stations/" + stationId);
export const getStationByNurseId = (stationId) => axios.get(url + "/stations/nurses/" + stationId);

export const createNurse = (nurseObj) => axios.post(url + "/nurses", nurseObj);
export const getNurses = () => axios.get(url + "/nurses");
export const updateNurseById = (nurseId, nurseObj) => axios.put(url + "/nurses/" + nurseId, nurseObj);
export const deleteNurseById = (nurseId) => axios.delete(url + "/nurses/" + nurseId);
export const getNursesByStationId = (nurseId) => axios.get(url + "/nurses/stations/" + nurseId);
