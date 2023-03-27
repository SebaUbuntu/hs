namespace SqlCommandDemo
{
    partial class FormStart
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.buttonCreaDB = new System.Windows.Forms.Button();
            this.buttonPopola = new System.Windows.Forms.Button();
            this.dataGridViewProva = new System.Windows.Forms.DataGridView();
            this.buttonCarica = new System.Windows.Forms.Button();
            this.buttonSalva = new System.Windows.Forms.Button();
            this.buttonDatiConParametri = new System.Windows.Forms.Button();
            this.textBoxNome = new System.Windows.Forms.TextBox();
            this.buttonDatiConFunzione = new System.Windows.Forms.Button();
            this.labelTotale = new System.Windows.Forms.Label();
            this.buttonCancellaDati = new System.Windows.Forms.Button();
            this.buttonCancellaDatiMySql = new System.Windows.Forms.Button();
            this.buttonSalvaDatiMySql = new System.Windows.Forms.Button();
            this.buttonMostraDatiMySql = new System.Windows.Forms.Button();
            this.buttonPopolaMySql = new System.Windows.Forms.Button();
            this.buttonCreaDBMySql = new System.Windows.Forms.Button();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.textBoxNomeMySql = new System.Windows.Forms.TextBox();
            this.buttonDatiConParametriMySql = new System.Windows.Forms.Button();
            this.buttonCreaUtente = new System.Windows.Forms.Button();
            this.buttonSelectConUtenteProva = new System.Windows.Forms.Button();
            this.buttonAssegnaDiritti = new System.Windows.Forms.Button();
            this.buttonRevocaDiritti = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewProva)).BeginInit();
            this.SuspendLayout();
            // 
            // buttonCreaDB
            // 
            this.buttonCreaDB.Location = new System.Drawing.Point(12, 39);
            this.buttonCreaDB.Name = "buttonCreaDB";
            this.buttonCreaDB.Size = new System.Drawing.Size(75, 23);
            this.buttonCreaDB.TabIndex = 0;
            this.buttonCreaDB.Text = "Crea DB";
            this.buttonCreaDB.UseVisualStyleBackColor = true;
            this.buttonCreaDB.Click += new System.EventHandler(this.buttonCreaDB_Click);
            // 
            // buttonPopola
            // 
            this.buttonPopola.Location = new System.Drawing.Point(12, 68);
            this.buttonPopola.Name = "buttonPopola";
            this.buttonPopola.Size = new System.Drawing.Size(75, 23);
            this.buttonPopola.TabIndex = 1;
            this.buttonPopola.Text = "Popola";
            this.buttonPopola.UseVisualStyleBackColor = true;
            this.buttonPopola.Click += new System.EventHandler(this.buttonPopola_Click);
            // 
            // dataGridViewProva
            // 
            this.dataGridViewProva.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridViewProva.Location = new System.Drawing.Point(12, 164);
            this.dataGridViewProva.Name = "dataGridViewProva";
            this.dataGridViewProva.Size = new System.Drawing.Size(602, 212);
            this.dataGridViewProva.TabIndex = 2;
            // 
            // buttonCarica
            // 
            this.buttonCarica.Location = new System.Drawing.Point(12, 97);
            this.buttonCarica.Name = "buttonCarica";
            this.buttonCarica.Size = new System.Drawing.Size(75, 23);
            this.buttonCarica.TabIndex = 3;
            this.buttonCarica.Text = "Mostra Dati";
            this.buttonCarica.UseVisualStyleBackColor = true;
            this.buttonCarica.Click += new System.EventHandler(this.buttonCarica_Click);
            // 
            // buttonSalva
            // 
            this.buttonSalva.Location = new System.Drawing.Point(180, 39);
            this.buttonSalva.Name = "buttonSalva";
            this.buttonSalva.Size = new System.Drawing.Size(75, 23);
            this.buttonSalva.TabIndex = 4;
            this.buttonSalva.Text = "Salva Dati";
            this.buttonSalva.UseVisualStyleBackColor = true;
            this.buttonSalva.Click += new System.EventHandler(this.buttonSalva_Click);
            // 
            // buttonDatiConParametri
            // 
            this.buttonDatiConParametri.Location = new System.Drawing.Point(180, 68);
            this.buttonDatiConParametri.Name = "buttonDatiConParametri";
            this.buttonDatiConParametri.Size = new System.Drawing.Size(265, 23);
            this.buttonDatiConParametri.TabIndex = 5;
            this.buttonDatiConParametri.Text = "Mostra Fatture del Cliente: (Select con Parametri)";
            this.buttonDatiConParametri.UseVisualStyleBackColor = true;
            this.buttonDatiConParametri.Click += new System.EventHandler(this.buttonDatiConParametri_Click);
            // 
            // textBoxNome
            // 
            this.textBoxNome.Location = new System.Drawing.Point(451, 70);
            this.textBoxNome.Name = "textBoxNome";
            this.textBoxNome.Size = new System.Drawing.Size(168, 20);
            this.textBoxNome.TabIndex = 6;
            // 
            // buttonDatiConFunzione
            // 
            this.buttonDatiConFunzione.Location = new System.Drawing.Point(180, 97);
            this.buttonDatiConFunzione.Name = "buttonDatiConFunzione";
            this.buttonDatiConFunzione.Size = new System.Drawing.Size(265, 23);
            this.buttonDatiConFunzione.TabIndex = 7;
            this.buttonDatiConFunzione.Text = "Mostra Fatture del Cliente (invoca Function)";
            this.buttonDatiConFunzione.UseVisualStyleBackColor = true;
            this.buttonDatiConFunzione.Click += new System.EventHandler(this.buttonDatiConFunzione_Click);
            // 
            // labelTotale
            // 
            this.labelTotale.AutoSize = true;
            this.labelTotale.Location = new System.Drawing.Point(462, 359);
            this.labelTotale.Name = "labelTotale";
            this.labelTotale.Size = new System.Drawing.Size(0, 13);
            this.labelTotale.TabIndex = 8;
            // 
            // buttonCancellaDati
            // 
            this.buttonCancellaDati.Location = new System.Drawing.Point(180, 126);
            this.buttonCancellaDati.Name = "buttonCancellaDati";
            this.buttonCancellaDati.Size = new System.Drawing.Size(129, 23);
            this.buttonCancellaDati.TabIndex = 9;
            this.buttonCancellaDati.Text = "Cancella tutti Record";
            this.buttonCancellaDati.UseVisualStyleBackColor = true;
            this.buttonCancellaDati.Click += new System.EventHandler(this.buttonCancellaDati_Click);
            // 
            // buttonCancellaDatiMySql
            // 
            this.buttonCancellaDatiMySql.Location = new System.Drawing.Point(180, 479);
            this.buttonCancellaDatiMySql.Name = "buttonCancellaDatiMySql";
            this.buttonCancellaDatiMySql.Size = new System.Drawing.Size(129, 23);
            this.buttonCancellaDatiMySql.TabIndex = 16;
            this.buttonCancellaDatiMySql.Text = "Cancella tutti Record";
            this.buttonCancellaDatiMySql.UseVisualStyleBackColor = true;
            this.buttonCancellaDatiMySql.Click += new System.EventHandler(this.buttonCancellaDatiMySql_Click);
            // 
            // buttonSalvaDatiMySql
            // 
            this.buttonSalvaDatiMySql.Location = new System.Drawing.Point(180, 421);
            this.buttonSalvaDatiMySql.Name = "buttonSalvaDatiMySql";
            this.buttonSalvaDatiMySql.Size = new System.Drawing.Size(75, 23);
            this.buttonSalvaDatiMySql.TabIndex = 13;
            this.buttonSalvaDatiMySql.Text = "Salva Dati";
            this.buttonSalvaDatiMySql.UseVisualStyleBackColor = true;
            this.buttonSalvaDatiMySql.Click += new System.EventHandler(this.buttonSalvaDatiMySql_Click);
            // 
            // buttonMostraDatiMySql
            // 
            this.buttonMostraDatiMySql.Location = new System.Drawing.Point(12, 479);
            this.buttonMostraDatiMySql.Name = "buttonMostraDatiMySql";
            this.buttonMostraDatiMySql.Size = new System.Drawing.Size(75, 23);
            this.buttonMostraDatiMySql.TabIndex = 12;
            this.buttonMostraDatiMySql.Text = "Mostra Dati";
            this.buttonMostraDatiMySql.UseVisualStyleBackColor = true;
            this.buttonMostraDatiMySql.Click += new System.EventHandler(this.buttonMostraDatiMySql_Click);
            // 
            // buttonPopolaMySql
            // 
            this.buttonPopolaMySql.Location = new System.Drawing.Point(12, 450);
            this.buttonPopolaMySql.Name = "buttonPopolaMySql";
            this.buttonPopolaMySql.Size = new System.Drawing.Size(75, 23);
            this.buttonPopolaMySql.TabIndex = 11;
            this.buttonPopolaMySql.Text = "Popola";
            this.buttonPopolaMySql.UseVisualStyleBackColor = true;
            this.buttonPopolaMySql.Click += new System.EventHandler(this.buttonPopolaMySql_Click);
            // 
            // buttonCreaDBMySql
            // 
            this.buttonCreaDBMySql.Location = new System.Drawing.Point(12, 421);
            this.buttonCreaDBMySql.Name = "buttonCreaDBMySql";
            this.buttonCreaDBMySql.Size = new System.Drawing.Size(75, 23);
            this.buttonCreaDBMySql.TabIndex = 10;
            this.buttonCreaDBMySql.Text = "Crea DB";
            this.buttonCreaDBMySql.UseVisualStyleBackColor = true;
            this.buttonCreaDBMySql.Click += new System.EventHandler(this.buttonCreaDBMySql_Click);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Microsoft Sans Serif", 14F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label1.ForeColor = System.Drawing.Color.DarkRed;
            this.label1.Location = new System.Drawing.Point(15, 1);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(129, 24);
            this.label1.TabIndex = 17;
            this.label1.Text = "SQL SERVER";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Font = new System.Drawing.Font("Microsoft Sans Serif", 14F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label2.ForeColor = System.Drawing.Color.DarkRed;
            this.label2.Location = new System.Drawing.Point(14, 390);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(72, 24);
            this.label2.TabIndex = 18;
            this.label2.Text = "MySQL";
            // 
            // textBoxNomeMySql
            // 
            this.textBoxNomeMySql.Location = new System.Drawing.Point(451, 452);
            this.textBoxNomeMySql.Name = "textBoxNomeMySql";
            this.textBoxNomeMySql.Size = new System.Drawing.Size(168, 20);
            this.textBoxNomeMySql.TabIndex = 20;
            // 
            // buttonDatiConParametriMySql
            // 
            this.buttonDatiConParametriMySql.Location = new System.Drawing.Point(180, 450);
            this.buttonDatiConParametriMySql.Name = "buttonDatiConParametriMySql";
            this.buttonDatiConParametriMySql.Size = new System.Drawing.Size(265, 23);
            this.buttonDatiConParametriMySql.TabIndex = 19;
            this.buttonDatiConParametriMySql.Text = "Mostra Fatture del Cliente: (Select con Parametri)";
            this.buttonDatiConParametriMySql.UseVisualStyleBackColor = true;
            this.buttonDatiConParametriMySql.Click += new System.EventHandler(this.buttonDatiConParametriMySql_Click);
            // 
            // buttonCreaUtente
            // 
            this.buttonCreaUtente.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.buttonCreaUtente.Location = new System.Drawing.Point(12, 508);
            this.buttonCreaUtente.Name = "buttonCreaUtente";
            this.buttonCreaUtente.Size = new System.Drawing.Size(602, 40);
            this.buttonCreaUtente.TabIndex = 21;
            this.buttonCreaUtente.Text = "Crea Utente Prova: CREATE USER \'prova\'@\'localhost\' IDENTIFIED BY \'12345678\';";
            this.buttonCreaUtente.UseVisualStyleBackColor = true;
            this.buttonCreaUtente.Click += new System.EventHandler(this.buttonCreaUtente_Click);
            // 
            // buttonSelectConUtenteProva
            // 
            this.buttonSelectConUtenteProva.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.buttonSelectConUtenteProva.Location = new System.Drawing.Point(12, 554);
            this.buttonSelectConUtenteProva.Name = "buttonSelectConUtenteProva";
            this.buttonSelectConUtenteProva.Size = new System.Drawing.Size(602, 35);
            this.buttonSelectConUtenteProva.TabIndex = 22;
            this.buttonSelectConUtenteProva.Text = "esegui SELECT * FROM Clienti con utente Prova";
            this.buttonSelectConUtenteProva.UseVisualStyleBackColor = true;
            this.buttonSelectConUtenteProva.Click += new System.EventHandler(this.buttonSelectConUtenteProva_Click);
            // 
            // buttonAssegnaDiritti
            // 
            this.buttonAssegnaDiritti.Font = new System.Drawing.Font("Microsoft Sans Serif", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.buttonAssegnaDiritti.Location = new System.Drawing.Point(12, 597);
            this.buttonAssegnaDiritti.Name = "buttonAssegnaDiritti";
            this.buttonAssegnaDiritti.Size = new System.Drawing.Size(602, 39);
            this.buttonAssegnaDiritti.TabIndex = 23;
            this.buttonAssegnaDiritti.Text = "Assegna diritti a utente prova: GRANT SELECT ON prova.* TO prova@localhost WITH G" +
    "RANT OPTION;";
            this.buttonAssegnaDiritti.UseVisualStyleBackColor = true;
            this.buttonAssegnaDiritti.Click += new System.EventHandler(this.buttonAssegnaDiritti_Click);
            // 
            // buttonRevocaDiritti
            // 
            this.buttonRevocaDiritti.Font = new System.Drawing.Font("Microsoft Sans Serif", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.buttonRevocaDiritti.Location = new System.Drawing.Point(12, 642);
            this.buttonRevocaDiritti.Name = "buttonRevocaDiritti";
            this.buttonRevocaDiritti.Size = new System.Drawing.Size(602, 39);
            this.buttonRevocaDiritti.TabIndex = 24;
            this.buttonRevocaDiritti.Text = "Revoca diritti a utente prova: REVOKE SELECT ON prova.* TO prova@localhost";
            this.buttonRevocaDiritti.UseVisualStyleBackColor = true;
            this.buttonRevocaDiritti.Click += new System.EventHandler(this.buttonRevocaDiritti_Click);
            // 
            // FormStart
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(626, 688);
            this.Controls.Add(this.buttonRevocaDiritti);
            this.Controls.Add(this.buttonAssegnaDiritti);
            this.Controls.Add(this.buttonSelectConUtenteProva);
            this.Controls.Add(this.buttonCreaUtente);
            this.Controls.Add(this.textBoxNomeMySql);
            this.Controls.Add(this.buttonDatiConParametriMySql);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.buttonCancellaDatiMySql);
            this.Controls.Add(this.buttonSalvaDatiMySql);
            this.Controls.Add(this.buttonMostraDatiMySql);
            this.Controls.Add(this.buttonPopolaMySql);
            this.Controls.Add(this.buttonCreaDBMySql);
            this.Controls.Add(this.buttonCancellaDati);
            this.Controls.Add(this.labelTotale);
            this.Controls.Add(this.buttonDatiConFunzione);
            this.Controls.Add(this.textBoxNome);
            this.Controls.Add(this.buttonDatiConParametri);
            this.Controls.Add(this.buttonSalva);
            this.Controls.Add(this.buttonCarica);
            this.Controls.Add(this.dataGridViewProva);
            this.Controls.Add(this.buttonPopola);
            this.Controls.Add(this.buttonCreaDB);
            this.Name = "FormStart";
            this.Text = "Sql Command DEMO";
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewProva)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button buttonCreaDB;
        private System.Windows.Forms.Button buttonPopola;
        private System.Windows.Forms.DataGridView dataGridViewProva;
        private System.Windows.Forms.Button buttonCarica;
        private System.Windows.Forms.Button buttonSalva;
        private System.Windows.Forms.Button buttonDatiConParametri;
        private System.Windows.Forms.TextBox textBoxNome;
        private System.Windows.Forms.Button buttonDatiConFunzione;
        private System.Windows.Forms.Label labelTotale;
        private System.Windows.Forms.Button buttonCancellaDati;
        private System.Windows.Forms.Button buttonCancellaDatiMySql;
        private System.Windows.Forms.Button buttonSalvaDatiMySql;
        private System.Windows.Forms.Button buttonMostraDatiMySql;
        private System.Windows.Forms.Button buttonPopolaMySql;
        private System.Windows.Forms.Button buttonCreaDBMySql;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.TextBox textBoxNomeMySql;
        private System.Windows.Forms.Button buttonDatiConParametriMySql;
        private System.Windows.Forms.Button buttonCreaUtente;
        private System.Windows.Forms.Button buttonSelectConUtenteProva;
        private System.Windows.Forms.Button buttonAssegnaDiritti;
        private System.Windows.Forms.Button buttonRevocaDiritti;
    }
}

