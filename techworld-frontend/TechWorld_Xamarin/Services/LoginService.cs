using Newtonsoft.Json;
using System.Collections.ObjectModel;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using TechWorld_Xamarin.Model;

namespace TechWorld_Xamarin.Services
{
    public class LoginService
    {
        public async Task<Cliente> Login(string username, string password)
        {
            var client = new HttpClient();
            var response = client.GetAsync("http://192.168.1.98:8080/client/login/" + username + "/" + password).GetAwaiter().GetResult();
            var user = response.Content.ReadAsStringAsync();
            return JsonConvert.DeserializeObject<Cliente>(user.Result);
        }

        public async Task<Cliente> Register(Cliente utente)
        {
            HttpClient client = new HttpClient();
            string user = JsonConvert.SerializeObject(utente);
            StringContent content = new StringContent(user, Encoding.UTF8, "application/json");
            var response = client.PostAsync("http://192.168.1.98:8080/client", content).GetAwaiter().GetResult();
            var userNow = response.Content.ReadAsStringAsync();
            return JsonConvert.DeserializeObject<Cliente>(userNow.Result);
        }

        public async Task<ObservableCollection<Ordine>> RicavaOrdini(string username)
        {
            HttpClient client = new HttpClient();
            var response = client.GetAsync("http://192.168.1.98:8080/ordini/byUser/" + username).GetAwaiter().GetResult();
            var list = response.Content.ReadAsStringAsync();
            var items = JsonConvert.DeserializeObject<Ordine[]>(list.Result);
            return new ObservableCollection<Ordine>(items);
        }
    }
}

