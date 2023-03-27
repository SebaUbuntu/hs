
namespace WindowsFormsUtenti
{
    partial class FormUtenti
    {
        /// <summary>
        /// Variabile di progettazione necessaria.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Pulire le risorse in uso.
        /// </summary>
        /// <param name="disposing">ha valore true se le risorse gestite devono essere eliminate, false in caso contrario.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Codice generato da Progettazione Windows Form

        /// <summary>
        /// Metodo necessario per il supporto della finestra di progettazione. Non modificare
        /// il contenuto del metodo con l'editor di codice.
        /// </summary>
        private void InitializeComponent()
        {
            this.dataGridViewUtenti = new System.Windows.Forms.DataGridView();
            this.buttonLogin = new System.Windows.Forms.Button();
            this.buttonNuovoUtente = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewUtenti)).BeginInit();
            this.SuspendLayout();
            // 
            // dataGridViewUtenti
            // 
            this.dataGridViewUtenti.AllowUserToAddRows = false;
            this.dataGridViewUtenti.AllowUserToDeleteRows = false;
            this.dataGridViewUtenti.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridViewUtenti.Location = new System.Drawing.Point(12, 12);
            this.dataGridViewUtenti.Name = "dataGridViewUtenti";
            this.dataGridViewUtenti.ReadOnly = true;
            this.dataGridViewUtenti.Size = new System.Drawing.Size(695, 426);
            this.dataGridViewUtenti.TabIndex = 0;
            // 
            // buttonLogin
            // 
            this.buttonLogin.Location = new System.Drawing.Point(713, 12);
            this.buttonLogin.Name = "buttonLogin";
            this.buttonLogin.Size = new System.Drawing.Size(75, 23);
            this.buttonLogin.TabIndex = 1;
            this.buttonLogin.Text = "Login";
            this.buttonLogin.UseVisualStyleBackColor = true;
            this.buttonLogin.Click += new System.EventHandler(this.buttonLogin_Click);
            // 
            // buttonNuovoUtente
            // 
            this.buttonNuovoUtente.Location = new System.Drawing.Point(714, 42);
            this.buttonNuovoUtente.Name = "buttonNuovoUtente";
            this.buttonNuovoUtente.Size = new System.Drawing.Size(75, 39);
            this.buttonNuovoUtente.TabIndex = 2;
            this.buttonNuovoUtente.Text = "Nuovo utente";
            this.buttonNuovoUtente.UseVisualStyleBackColor = true;
            this.buttonNuovoUtente.Click += new System.EventHandler(this.buttonNuovoUtente_Click);
            // 
            // FormUtenti
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.buttonNuovoUtente);
            this.Controls.Add(this.buttonLogin);
            this.Controls.Add(this.dataGridViewUtenti);
            this.Name = "FormUtenti";
            this.Text = "FormUtenti";
            this.Load += new System.EventHandler(this.FormUtenti_Load);
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewUtenti)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.DataGridView dataGridViewUtenti;
        private System.Windows.Forms.Button buttonLogin;
        private System.Windows.Forms.Button buttonNuovoUtente;
    }
}

