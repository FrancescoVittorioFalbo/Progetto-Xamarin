using Newtonsoft.Json;
using System.Collections.ObjectModel;
using System.Net.Http;
using System.Threading.Tasks;
using TechWorld_Xamarin.Model;

namespace TechWorld_Xamarin.Services
{
    public class Prodotto_CategoriaServices
    {
        public async Task<ObservableCollection<Prodotto>> changeCategoria(string name)
        {
            var store = new HttpClient();
            var response = store.GetAsync("http://192.168.1.98:8080/categ/by_name/" + name).GetAwaiter().GetResult();
            var list = response.Content.ReadAsStringAsync();
            var items = JsonConvert.DeserializeObject<Prodotto[]>(list.Result);
            return new ObservableCollection<Prodotto>(items);
        }

    }
}
