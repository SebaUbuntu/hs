﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.Json;

namespace ChatTCP.Common
{
    public static class Protocol
    {
        /// <summary>
        /// Porta di default del protocollo
        /// </summary>
        public const int DEFAULT_PORT = 8221;

        /// <summary>
        /// Classe di base che rappresenta un messaggio
        /// </summary>
        public class BaseMessage
        {
            virtual public string action { get; set; }

            virtual public string ToJson()
            {
                return JsonSerializer.Serialize(this);
            }
        }

        /// <summary>
        /// Messaggio che rappresenta un tentativo di login da parte di un client
        /// </summary>
        public class LoginMessage : BaseMessage
        {
            override public string action => "login";

            public string username { get; set; }
            public string password { get; set; }

            override public string ToJson()
            {
                return JsonSerializer.Serialize(this);
            }
        }

        /// <summary>
        /// Messaggio che rappresenta un tentativo di registrazione da parte di un client
        /// </summary>
        public class RegisterMessage : BaseMessage
        {
            override public string action => "register";

            public string username { get; set; }
            public string password { get; set; }
            public string nome { get; set; }
            public string cognome { get; set; }
            public string email { get; set; }

            override public string ToJson()
            {
                return JsonSerializer.Serialize(this);
            }
        }

        /// <summary>
        /// Messaggio che rappresenta l'esito di un tentativo di login o registrazione
        /// </summary>
        public class LoginResultMessage : BaseMessage
        {
            override public string action => "login_result";

            public enum Result
            {
                Success,
                WrongCredentials,
                UserAlreadyExists,
            }

            public Result result { get; set; }

            override public string ToJson()
            {
                return JsonSerializer.Serialize(this);
            }
        }

        /// <summary>
        /// Messaggio che rappresenta l'invio di un messaggio da parte di un client verso un server
        /// </summary>
        public class SendMessageMessage : BaseMessage
        {
            override public string action => "send_message";

            public List<string> to_users { get; set; }
            public string message { get; set; }

            override public string ToJson()
            {
                return JsonSerializer.Serialize(this);
            }
        }

        /// <summary>
        /// Messaggio che rappresenta l'invio di un messaggio da parte di un client
        /// </summary>
        public class MessageReceivedMessage : BaseMessage
        {
            override public string action => "message_received";

            public long timestamp { get; set; }
            public string username { get; set; }
            public List<string> to_users { get; set; }
            public string message { get; set; }

            override public string ToJson()
            {
                return JsonSerializer.Serialize(this);
            }
        }

        /// <summary>
        /// Aggiorna le informazioni sull'utente
        /// </summary>
        public class UpdateUserInfoMessage : BaseMessage
        {
            override public string action => "update_user_info";

            public string nome { get; set; }
            public string cognome { get; set; }
            public string email { get; set; }

            override public string ToJson()
            {
                return JsonSerializer.Serialize(this);
            }
        }

        /// <summary>
        /// Cambia la password di un utente
        /// </summary>
        public class ChangePasswordMessage : BaseMessage
        {
            override public string action => "change_password";

            public string new_password { get; set; }

            public override string ToJson()
            {
                return JsonSerializer.Serialize(this);
            }
        }

        /// <summary>
        /// Fa il logout di un utente senza interrompere la connessione
        /// </summary>
        public class LogoutMessage : BaseMessage
        {
            override public string action => "logout";

            public override string ToJson()
            {
                return JsonSerializer.Serialize(this);
            }
        }

        public class UpdatedOnlineUsersMessage : BaseMessage
        {
            override public string action => "updated_online_users";

            public List<string> online_users { get; set; }

            public override string ToJson()
            {
                return JsonSerializer.Serialize(this);
            }
        }

        /// <summary>
        /// Converti una stringa JSON in oggetto
        /// </summary>
        /// <param name="json">Il messaggio ricevuto</param>
        /// <returns>Un oggetto di una classe che eredita <c>BaseMessage</c></returns>
        public static BaseMessage FromJson(string json)
        {
            if (json == null)
            {
                return null;
            }

            try
            {
                var baseMessage = JsonSerializer.Deserialize<BaseMessage>(json);

                switch (baseMessage.action)
                {
                    case "login":
                        return JsonSerializer.Deserialize<LoginMessage>(json);
                    case "register":
                        return JsonSerializer.Deserialize<RegisterMessage>(json);
                    case "login_result":
                        return JsonSerializer.Deserialize<LoginResultMessage>(json);
                    case "send_message":
                        return JsonSerializer.Deserialize<SendMessageMessage>(json);
                    case "message_received":
                        return JsonSerializer.Deserialize<MessageReceivedMessage>(json);
                    case "update_user_info":
                        return JsonSerializer.Deserialize<UpdateUserInfoMessage>(json);
                    case "change_password":
                        return JsonSerializer.Deserialize<ChangePasswordMessage>(json);
                    case "logout":
                        return JsonSerializer.Deserialize<LogoutMessage>(json);
                    case "updated_online_users":
                        return JsonSerializer.Deserialize<UpdatedOnlineUsersMessage>(json);
                    default:
                        // throw new Exception("Unknown action");
                        return null;
                }
            }
            catch (Exception e)
            {
                throw e;
                //return null;
            }
        }

        public static byte[] EncodeMessage(string s)
        {
            if (s == null)
            {
                return null;
            }

            if (s.Contains('\0'))
            {
                throw new Exception("Message can't contain NUL");
            }

            return Encoding.UTF8.GetBytes(s + '\0');
        }

        public static string DecodeMessage(byte[] bytes, int numBytes)
        {
            if (bytes == null || numBytes < 0)
            {
                return null;
            }

            return Encoding.UTF8.GetString(bytes, 0, numBytes);
        }

        /// <summary>
        /// Elabora i dati ricevuti dal socket e separa i pacchetti dove serve
        /// </summary>
        /// <param name="buffer">Il buffer dove vengono salvati i dati non processati</param>
        /// <returns>Lista di messaggi pronti per essere deserializzati dal JSON parser, vuoto se il pacchetto non è completo</returns>
        public static List<string> GetMessages(ref string buffer)
        {
            var messagesList = new List<string>();

            if (string.IsNullOrEmpty(buffer))
            {
                return messagesList;
            }

            // Continuiamo a processare la stringa fino a che non rimangono caratteri NUL
            int nullCharIndex;
            while ((nullCharIndex = buffer.IndexOf('\0')) != -1)
            {
                var message = buffer.Substring(0, nullCharIndex);
                messagesList.Add(message);
                buffer = buffer.Substring(nullCharIndex + 1);
            }

            return messagesList;
        }

        public static DateTimeOffset DateTimeOffsetNow => DateTimeOffset.Now;

        public static long DateTimeOffsetToUNIXTimestamp(DateTimeOffset dateTimeOffset)
        {
            return dateTimeOffset.ToUniversalTime().ToUnixTimeMilliseconds();
        }

        public static DateTimeOffset UNIXTimestampToDateTimeOffset(long timestamp)
        {
            return DateTimeOffset.FromUnixTimeMilliseconds(timestamp).ToLocalTime();
        }
    }
}
