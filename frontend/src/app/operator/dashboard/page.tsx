import { client } from "@/api/client";

export const dynamic = "force-dynamic";

export default async function OperatorDashboard() {
  const { data, error } = await client.GET("/operator/dashboard/stats");

  if (error) {
    return <div className="p-8 text-red-500">Error loading stats.</div>;
  }

  return (
    <div className="p-8 bg-gray-50 min-h-screen">
      <h1 className="text-3xl font-bold text-gray-800 mb-8">Dashboard Operatore</h1>
      
      <div className="grid grid-cols-1 md:grid-cols-3 gap-6 mb-8">
        <div className="bg-white p-6 rounded shadow border-t-4 border-blue-500">
          <h2 className="text-gray-500 font-medium">Totale auto sostituite</h2>
          <p className="text-4xl font-bold text-gray-800 mt-2">{data?.totalReplacedCars || 0}</p>
        </div>
        <div className="bg-white p-6 rounded shadow border-t-4 border-green-500">
          <h2 className="text-gray-500 font-medium">Totale richieste</h2>
          <p className="text-4xl font-bold text-gray-800 mt-2">{data?.totalRequests || 0}</p>
        </div>
        <div className="bg-white p-6 rounded shadow border-t-4 border-purple-500">
          <h2 className="text-gray-500 font-medium">Spese totali</h2>
          <p className="text-4xl font-bold text-gray-800 mt-2">€ {data?.totalExpenses?.toLocaleString() || 0}</p>
        </div>
      </div>

      <div className="bg-white p-6 rounded shadow">
        <div className="flex justify-between items-center mb-4">
          <h2 className="text-xl font-bold text-gray-800">Azioni Rapide</h2>
        </div>
        <div className="flex gap-4">
          <a href="/operator/requests" className="px-4 py-2 bg-gray-100 text-gray-800 font-medium rounded hover:bg-gray-200">
            Vedi tutte le Richieste
          </a>
          <a href="/operator/bookings" className="px-4 py-2 bg-blue-600 text-white font-medium rounded hover:bg-blue-700">
            Prenota Auto Sostitutiva
          </a>
        </div>
      </div>
    </div>
  );
}
