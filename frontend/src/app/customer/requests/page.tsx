import { client } from "@/api/client";

export const dynamic = "force-dynamic";

export default async function CustomerRequestsPage() {
  const { data, error } = await client.GET("/customer/requests", {
    params: { query: { page: 0, size: 10 } }
  });

  if (error) {
    return <div className="p-8 text-red-500">Error loading requests.</div>;
  }

  return (
    <div className="p-8 bg-gray-50 min-h-screen">
      <div className="flex justify-between items-center mb-6">
        <h1 className="text-3xl font-bold text-gray-800">Storico Auto Sostitutiva</h1>
        <a href="/customer/requests/new" className="px-4 py-2 bg-blue-600 text-white rounded font-medium hover:bg-blue-700">
          Richiedi Nuova
        </a>
      </div>

      <div className="bg-white rounded shadow p-6">
        <table className="w-full text-left border-collapse">
          <thead>
            <tr className="border-b text-gray-600 uppercase text-sm">
              <th className="pb-3 text-gray-900">ID</th>
              <th className="pb-3 text-gray-900">Targa</th>
              <th className="pb-3 text-gray-900">Veicolo</th>
              <th className="pb-3 text-gray-900">Alimentazione</th>
              <th className="pb-3 text-gray-900">Stato</th>
            </tr>
          </thead>
          <tbody>
            {data?.content?.length === 0 ? (
              <tr><td colSpan={5} className="py-4 text-center text-gray-900">Nessuna richiesta trovata.</td></tr>
            ) : (
              data?.content?.map(req => (
                <tr key={req.id} className="border-b last:border-0 hover:bg-gray-50">
                  <td className="py-3 font-medium text-gray-900">#{req.id}</td>
                  <td className="py-3 text-gray-900">{req.plate}</td>
                  <td className="py-3 text-gray-900">{req.vehicle}</td>
                  <td className="py-3 text-gray-900">{req.fuel}</td>
                  <td className="py-3 text-gray-900">
                    <span className={`px-2 py-1 rounded text-xs font-semibold ${
                      req.status === 'Consegnata' ? 'bg-green-100 text-green-800' :
                      req.status === 'In attesa' ? 'bg-yellow-100 text-yellow-800' :
                      'bg-gray-100 text-gray-800'
                    }`}>
                      {req.status}
                    </span>
                  </td>
                </tr>
              ))
            )}
          </tbody>
        </table>
      </div>
    </div>
  );
}
