
namespace WindowsFormsAppDataTable
{
    partial class WindowsFormClientiFatture
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
            this.dataGridViewClienti = new System.Windows.Forms.DataGridView();
            this.buttonMostraFatture = new System.Windows.Forms.Button();
            this.dataGridViewFatture = new System.Windows.Forms.DataGridView();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewClienti)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewFatture)).BeginInit();
            this.SuspendLayout();
            // 
            // dataGridViewClienti
            // 
            this.dataGridViewClienti.AllowUserToAddRows = false;
            this.dataGridViewClienti.AllowUserToDeleteRows = false;
            this.dataGridViewClienti.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridViewClienti.Location = new System.Drawing.Point(12, 12);
            this.dataGridViewClienti.Name = "dataGridViewClienti";
            this.dataGridViewClienti.ReadOnly = true;
            this.dataGridViewClienti.Size = new System.Drawing.Size(358, 426);
            this.dataGridViewClienti.TabIndex = 0;
            // 
            // buttonMostraFatture
            // 
            this.buttonMostraFatture.Location = new System.Drawing.Point(704, 12);
            this.buttonMostraFatture.Name = "buttonMostraFatture";
            this.buttonMostraFatture.Size = new System.Drawing.Size(84, 23);
            this.buttonMostraFatture.TabIndex = 2;
            this.buttonMostraFatture.Text = "Mostra fatture";
            this.buttonMostraFatture.UseVisualStyleBackColor = true;
            this.buttonMostraFatture.Click += new System.EventHandler(this.buttonMostraFatture_Click);
            // 
            // dataGridViewFatture
            // 
            this.dataGridViewFatture.AllowUserToAddRows = false;
            this.dataGridViewFatture.AllowUserToDeleteRows = false;
            this.dataGridViewFatture.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridViewFatture.Location = new System.Drawing.Point(528, 41);
            this.dataGridViewFatture.Name = "dataGridViewFatture";
            this.dataGridViewFatture.ReadOnly = true;
            this.dataGridViewFatture.Size = new System.Drawing.Size(260, 397);
            this.dataGridViewFatture.TabIndex = 3;
            // 
            // WindowsFormClientiFatture
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.dataGridViewFatture);
            this.Controls.Add(this.buttonMostraFatture);
            this.Controls.Add(this.dataGridViewClienti);
            this.Name = "WindowsFormClientiFatture";
            this.Text = "Form1";
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewClienti)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewFatture)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.DataGridView dataGridViewClienti;
        private System.Windows.Forms.Button buttonMostraFatture;
        private System.Windows.Forms.DataGridView dataGridViewFatture;
    }
}

