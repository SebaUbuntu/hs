namespace WindowsFormsAppMySQL2
{
    partial class FormMySQL2
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
            this.dataGridView = new System.Windows.Forms.DataGridView();
            this.comboBoxGenere = new System.Windows.Forms.ComboBox();
            this.textBoxYear = new System.Windows.Forms.TextBox();
            this.labelYear = new System.Windows.Forms.Label();
            this.labelGenere = new System.Windows.Forms.Label();
            this.buttonUpdate = new System.Windows.Forms.Button();
            this.buttonGetFilm = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView)).BeginInit();
            this.SuspendLayout();
            // 
            // dataGridView
            // 
            this.dataGridView.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridView.Location = new System.Drawing.Point(12, 129);
            this.dataGridView.Name = "dataGridView";
            this.dataGridView.Size = new System.Drawing.Size(776, 309);
            this.dataGridView.TabIndex = 0;
            // 
            // comboBoxGenere
            // 
            this.comboBoxGenere.FormattingEnabled = true;
            this.comboBoxGenere.Location = new System.Drawing.Point(60, 55);
            this.comboBoxGenere.Name = "comboBoxGenere";
            this.comboBoxGenere.Size = new System.Drawing.Size(121, 21);
            this.comboBoxGenere.TabIndex = 1;
            // 
            // textBoxYear
            // 
            this.textBoxYear.Location = new System.Drawing.Point(53, 22);
            this.textBoxYear.Name = "textBoxYear";
            this.textBoxYear.Size = new System.Drawing.Size(121, 20);
            this.textBoxYear.TabIndex = 2;
            // 
            // labelYear
            // 
            this.labelYear.AutoSize = true;
            this.labelYear.Location = new System.Drawing.Point(12, 22);
            this.labelYear.Name = "labelYear";
            this.labelYear.Size = new System.Drawing.Size(35, 13);
            this.labelYear.TabIndex = 3;
            this.labelYear.Text = "Anno:";
            // 
            // labelGenere
            // 
            this.labelGenere.AutoSize = true;
            this.labelGenere.Location = new System.Drawing.Point(12, 58);
            this.labelGenere.Name = "labelGenere";
            this.labelGenere.Size = new System.Drawing.Size(42, 13);
            this.labelGenere.TabIndex = 4;
            this.labelGenere.Text = "Genere";
            // 
            // buttonUpdate
            // 
            this.buttonUpdate.Location = new System.Drawing.Point(713, 12);
            this.buttonUpdate.Name = "buttonUpdate";
            this.buttonUpdate.Size = new System.Drawing.Size(75, 23);
            this.buttonUpdate.TabIndex = 5;
            this.buttonUpdate.Text = "Aggiorna";
            this.buttonUpdate.UseVisualStyleBackColor = true;
            this.buttonUpdate.Click += new System.EventHandler(this.buttonUpdate_Click);
            // 
            // buttonGetFilm
            // 
            this.buttonGetFilm.Location = new System.Drawing.Point(235, 22);
            this.buttonGetFilm.Name = "buttonGetFilm";
            this.buttonGetFilm.Size = new System.Drawing.Size(99, 23);
            this.buttonGetFilm.TabIndex = 6;
            this.buttonGetFilm.Text = "Carica tabella";
            this.buttonGetFilm.UseVisualStyleBackColor = true;
            this.buttonGetFilm.Click += new System.EventHandler(this.buttonGetFilm_Click);
            // 
            // FormMySQL2
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.buttonGetFilm);
            this.Controls.Add(this.buttonUpdate);
            this.Controls.Add(this.labelGenere);
            this.Controls.Add(this.labelYear);
            this.Controls.Add(this.textBoxYear);
            this.Controls.Add(this.comboBoxGenere);
            this.Controls.Add(this.dataGridView);
            this.Name = "FormMySQL2";
            this.Text = "FormMySQL2";
            this.Load += new System.EventHandler(this.FormMySQL2_Load);
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.DataGridView dataGridView;
        private System.Windows.Forms.ComboBox comboBoxGenere;
        private System.Windows.Forms.TextBox textBoxYear;
        private System.Windows.Forms.Label labelYear;
        private System.Windows.Forms.Label labelGenere;
        private System.Windows.Forms.Button buttonUpdate;
        private System.Windows.Forms.Button buttonGetFilm;
    }
}

