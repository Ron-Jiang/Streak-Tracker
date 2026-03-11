import axios from "axios";

const APIURL = "http://localhost:8080/api/habits";

export const getAllHabits = () => {
    return axios.get(APIURL);
}

export const createHabit = (habit) => {
    return axios.post(APIURL, habit);
}

export const updateHabit = (id, habit) => {
    return axios.put(`${APIURL}/${id}`, habit);
}

export const deleteHabit = (id) => {
    return axios.delete(`${APIURL}/${id}`);
}

export const completeHabitToday = (id) => {
    return axios.post(`${APIURL}/${id}/complete`);
}