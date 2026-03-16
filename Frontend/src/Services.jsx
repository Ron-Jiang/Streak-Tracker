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