import axios from "axios";

const axiosInstance = axios.create({
    baseURL: 'http://localhost:8080/api/habits',
    withCredentials: true
})

export const getAllHabits = () => axiosInstance.get('');
export const createHabit = (habit) => axiosInstance.post('', habit);
export const deleteHabit = (id) => axiosInstance.delete(`/${id}`);
export const completeHabitToday = (id) => axiosInstance.post(`/${id}/complete`);
export const habitsUpdate = () => axiosInstance.post('/check');
export const logout = () => {window.location.href='http://localhost:8080/logout'};

// const APIURL = "http://localhost:8080/api/habits";

// export const getAllHabits = () => {
//     return axios.get(APIURL);
// }

// export const createHabit = (habit) => {
//     return axios.post(APIURL, habit);
// }

// export const updateHabit = (id, habit) => {
//     return axios.put(`${APIURL}/${id}`, habit);
// }

// export const deleteHabit = (id) => {
//     return axios.delete(`${APIURL}/${id}`);
// }

// export const completeHabitToday = (id) => {
//     return axios.post(`${APIURL}/${id}/complete`);
// }

// export const habitsUpdate = () => {
//     return axios.post(`${APIURL}/check`);
// }