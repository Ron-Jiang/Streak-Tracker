function SideBar({ habits, onSelect }) {
    return (
        <>
            <div className="bg-black fixed h-screen w-20 flex flex-col pb-10">
                <button onClick={() => onSelect('home')} className="w-16 h-16 mt-10 rounded-full bg-yellow-300 mx-auto cursor-pointer shrink-0" />
                <div className="flex-1 overflow-y-auto flex flex-col items-center py-4">
                    {habits?.map(habit => (
                        <button key={habit.id} onClick={() => onSelect(habit.id)} className="w-16 h-16 mb-4 rounded-full bg-red-600 mx-auto cursor-pointer shrink-0" />
                    ))}
                </div>
                <button onClick={() => onSelect('add')} className="w-16 h-16 mb-10 rounded-full shrink-0 bg-yellow-300 mx-auto cursor-pointer" />
            </div>
        </>
    )
};


export default SideBar