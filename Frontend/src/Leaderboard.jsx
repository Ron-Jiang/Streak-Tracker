import { getLeaderboard } from "./Services";
import { useState, useEffect } from "react";

function Leaderboard() {
    const [habits, setHabits] = useState([]);

    useEffect(() => {
        getLeaderboard().then(({data}) => setHabits(data))
    }, []);

    return (
        <div className="flex flex-col gap-6 p-8 max-w-lg">
            <div>
                <p className="text-zinc-500 mb-1">Rankings</p>
                <h1 className="text-white font-bold text-4xl">Leaderboard</h1>
            </div>
            <div className="flex flex-col gap-2 overflow-y-auto">
                {habits.map((habit, index) => (
                    <div key={habit.id} className="bg-zinc-900 border border-zinc-700 rounded-lg px-4 py-3 flex items-center justify-between">
                        <div className="flex items-center gap-4">
                            <span className="text-zinc-500 text-s w-4">#{index+1}</span>
                            <div>
                                <p className="text-zinc-200 text-sm">{habit.habitName}</p>
                                <p className="text-zinc-500 text-xs">{habit.userId}</p>
                            </div>
                        </div>
                        <p className="text-amber-500 font-bold text-base">
                            {habit.currentStreak} DAY{habit.currentStreak !== 1 ? 'S' : ''}
                        </p>
                    </div>
                ))}
            </div>
        </div>
    )
}

export default Leaderboard;