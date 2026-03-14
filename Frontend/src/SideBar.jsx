function SideBar({ habits, onSelect }) {
    return (
        <>
            <div className="bg-black fixed h-screen w-20 flex flex-col pb-4 pt-16">
                <button onClick={() => onSelect('home')} className="w-16 h-16 rounded-full mt-4 bg-yellow-300 mx-auto cursor-pointer shrink-0">Home</ button>
                <div className="flex-1 overflow-y-auto flex flex-col items-center py-4">
                    {habits?.map(habit => (
                        <button key={habit.id} onClick={() => onSelect(habit.id)} className="w-16 h-16 mb-4 rounded-full bg-red-600 mx-auto cursor-pointer shrink-0" />
                    ))}
                </div>
                <button onClick={() => onSelect('add')} className="w-16 h-16 text-2xl rounded-full shrink-0 flex justify-center items-center bg-yellow-300 mx-auto cursor-pointer">Add</button>
            </div>
        </>
    )
};


export default SideBar