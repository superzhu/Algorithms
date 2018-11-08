
variable "sshKey" {
  type = "string"
}

variable "azure_subscription_id" {
  type = "string"
}
variable "azure_client_id" {
  type = "string"
}
variable "azure_tenant_id" {
  type = "string"
}
variable "azure_client_secret" {
  type = "string"
}

variable "cloudconfig_file" {
  description = "The location of the cloud init configuration file."
  default     = "cloudconfig.txt"
}

data "template_file" "cloudconfig" {
  template = "${file("${var.cloudconfig_file}")}"
}

data "template_cloudinit_config" "config" {
  gzip          = true
  base64_encode = true

  part {
    content_type = "text/cloud-config"
    content      = "${data.template_file.cloudconfig.rendered}"
  }
}

# Configure the Microsoft Azure Provider
provider "azurerm" {
  subscription_id = "${var.azure_subscription_id}"
  client_id       = "${var.azure_client_id}"
  tenant_id       = "${var.azure_tenant_id}"
  client_secret   = "${var.azure_client_secret}"
}


# Create a resource group
resource "azurerm_resource_group" "resourceGroup" {

  name     = "azhuzhi-tfcustom"
  location = "southcentralus"
}

resource "azurerm_template_deployment" "u18custom" {
   name                             = "azhuzhi-tfcustom"
   resource_group_name              = "azhuzhi-tfcustom"
   deployment_mode = "Incremental"  

   parameters {
      adminUsername                 = "plcm"
      userImageRG                   = "azhuzhi-custom-image"
      userImageName                 = "ubuntu18-custom-image"
      dnsLabelPrefix                = "vmfromcustomimage"

      sshKeyData                   = "${var.sshKey}"

      customData                   = "${data.template_cloudinit_config.config.rendered}"
   }  

   template_body = "${file("userimagevm.json")}"
}
