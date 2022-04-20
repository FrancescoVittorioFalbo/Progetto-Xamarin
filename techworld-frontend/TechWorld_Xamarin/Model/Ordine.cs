using System;

namespace TechWorld_Xamarin.Model
{
    public class Ordine
    {
        public int id { get; set; }
        public string citta { get; set; }
        public string via { get; set; }
        public int nr { get; set; }
        public Prodotto[] listaProdotti { get; set; }
        public Cliente utente { get; set; }
        public DateTime data { get; set; }
        
        public Ordine(int id, string citta, string via, int nr, Prodotto[] listaProdotti, Cliente utente)
        {
            this.id = id;
            this.citta = citta;
            this.via = via;
            this.nr = nr;
            this.listaProdotti = listaProdotti;
            this.utente = utente;
        }
    }
}
